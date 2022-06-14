package com.siemens.multitenancy.service.impl;

import com.alibaba.fastjson.JSON;
import com.siemens.multitenancy.entity.context.TenantContext;
import com.siemens.multitenancy.entity.param.ClientQueryParam;
import com.siemens.multitenancy.entity.param.ClientUpdateParam;
import com.siemens.multitenancy.entity.param.IConnectorInfoParam;
import com.siemens.multitenancy.entity.param.PageParam;
import com.siemens.multitenancy.entity.po.IConnectorInfo;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;
import com.siemens.multitenancy.mapper.ConfigurationMapper;
import com.siemens.multitenancy.repository.ConfigurationRepository;
import com.siemens.multitenancy.service.IConnectService;
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

import java.util.Optional;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 4:12 PM
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConnectServiceImpl implements IConnectService {
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
    public IConnectorInfoVo updateConnectorInfo(ClientUpdateParam updateParam) {
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(updateParam.getConnectorId());
        if (optional.isPresent()) {
            IConnectorInfo info = optional.get();
            ConfigurationMapper.updateParamMapToPo(info, updateParam);
            return ConfigurationMapper.infoPoMapToVo(info);
        } else {
            throw new RuntimeException(String.format("can't find connectorId %s in postgres db", updateParam.getConnectorId()));
        }
    }

    @Override
    public void registerConnector(String connectorId) {
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(connectorId);
        if (optional.isEmpty()) {
            throw new RuntimeException(String.format("can't find connectorId %s in postgres db", connectorId));
        }
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("tenantId", TenantContext.getCurrentTenant());
        params.add("connectorId", connectorId);
        String uri = UriComponentsBuilder.fromUriString("http://localhost:8887/api/v1/iconnector/registration")
                .queryParams(params)
                .toUriString();
        restTemplate.postForEntity(uri, JSON.toJSONString(ConfigurationMapper.infoPoMapToMq(optional.get())), String.class);
    }
}
