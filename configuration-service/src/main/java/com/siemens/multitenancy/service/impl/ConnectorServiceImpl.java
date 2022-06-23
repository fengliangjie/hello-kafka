package com.siemens.multitenancy.service.impl;

import com.alibaba.fastjson.JSON;
import com.siemens.multitenancy.entity.context.TenantContext;
import com.siemens.multitenancy.entity.param.*;
import com.siemens.multitenancy.entity.po.IConnectorInfo;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;
import com.siemens.multitenancy.mapper.ConfigurationMapper;
import com.siemens.multitenancy.repository.ConfigurationRepository;
import com.siemens.multitenancy.service.IConnectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import static com.siemens.multitenancy.constant.ConstantValues.*;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 4:12 PM
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConnectorServiceImpl implements IConnectorService {
    private final ConfigurationRepository configurationRepository;

    private final RestTemplate restTemplate;

    @Override
    public void info(IConnectorInfoParam infoParam) {
        IConnectorInfo info;
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(infoParam.getConnectorId());
        if (optional.isPresent()) {
            // update
            info = optional.get();
            ConfigurationMapper.infoParamMapToPo(info, infoParam);
        } else {
            // insert
            info = ConfigurationMapper.infoParamMapToPo(infoParam);
            configurationRepository.saveAndFlush(info);
        }
    }

    @Override
    public PageVo<IConnectorInfoVo> getConnectorInfos(ClientQueryParam clientQueryParam, PageParam pageParam) {
        Page<IConnectorInfo> page = configurationRepository.findAll(
                PageRequest.of(pageParam.getPageNumber(), pageParam.getPageSize()));
        return ConfigurationMapper.convert2VoPage(page, ConfigurationMapper::infoPoMapToVo);
    }

    @Override
    public IConnectorInfoVo updateConnectorInfo(String connectorId, List<IConnectorConfigParam> configParamList) {
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(connectorId);
        if (optional.isPresent()) {
            IConnectorInfo info = optional.get();
            ConfigurationMapper.updateParamMapToPo(info, configParamList);
            return ConfigurationMapper.infoPoMapToVo(info);
        } else {
            throw new RuntimeException(String.format("Can't find connectorId %s in postgres db", connectorId));
        }
    }

    @Override
    public IConnectorInfoVo uploadConnectorInfo(ClientUploadParam uploadParam) {
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(uploadParam.getConnectorId());
        if (optional.isEmpty()) {
            throw new RuntimeException(String.format("Can't find connectorId %s in postgres db", uploadParam.getConnectorId()));
        }
        IConnectorInfo info = optional.get();
        ConfigurationMapper.uploadParamMapToPo(info, uploadParam);
        return ConfigurationMapper.infoPoMapToVo(info);
    }

    @Override
    public IConnectorInfoVo registerConnector(String connectorId, boolean enable) {
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(connectorId);
        if (optional.isEmpty()) {
            throw new RuntimeException(String.format("Can't find connectorId %s in postgres db", connectorId));
        }
        // update enable status
        IConnectorInfo connectorInfo = optional.get();
        connectorInfo.setEnable(enable);
        // send iConnector info to message-handle
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(TENANT_ID, TenantContext.getCurrentTenant());
        params.add(CONNECTOR_ID, connectorId);
        String uri = UriComponentsBuilder.fromUriString(MESSAGE_HANDLER_URL)
                .queryParams(params)
                .toUriString();
        restTemplate.postForEntity(uri, JSON.toJSONString(ConfigurationMapper.infoPoMapToMq(connectorInfo)), String.class);
        return ConfigurationMapper.infoPoMapToVo(connectorInfo);
    }

    @Override
    public IConnectorInfoVo getConnectorInfo(String connectorId) {
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(connectorId);
        if (optional.isPresent()) {
            return ConfigurationMapper.infoPoMapToVo(optional.get());
        } else {
            throw new RuntimeException(String.format("Can't find connectorId %s in postgres db", connectorId));
        }
    }

    @Override
    public void deleteConnector(String connectorId) {
        configurationRepository.deleteByConnectorId(connectorId);
    }
}
