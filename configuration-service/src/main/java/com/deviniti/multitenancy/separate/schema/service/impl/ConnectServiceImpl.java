package com.deviniti.multitenancy.separate.schema.service.impl;

import com.alibaba.fastjson.JSON;
import com.deviniti.multitenancy.separate.schema.entity.context.TenantContext;
import com.deviniti.multitenancy.separate.schema.entity.param.ClientQueryParam;
import com.deviniti.multitenancy.separate.schema.entity.param.ClientUpdateParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorConfig;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorInfo;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorModule;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;
import com.deviniti.multitenancy.separate.schema.entity.vo.PageVo;
import com.deviniti.multitenancy.separate.schema.mapper.ConfigurationMapper;
import com.deviniti.multitenancy.separate.schema.repository.ConfigurationRepository;
import com.deviniti.multitenancy.separate.schema.service.IConnectService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void info(IConnectorInfoParam param) {
        IConnectorInfo info;
        IConnectorInfoParam.Data data = param.getData();
        List<IConnectorModule> modules = data.getModules().stream().map(t -> IConnectorModule.builder().name(t.getName()).version(t.getVersion()).status(t.getStatus()).build()).collect(Collectors.toList());
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(param.getConnectorId());
        if (optional.isPresent()) {
            // update
            info = optional.get();
            info.setStatus(data.getStatus());
            info.setCollectStatus(data.getCollectStatus());
            info.setVersion(data.getVersion());
            info.setDateTime(param.getDateTime());
            info.getModules().clear();
            info.getModules().addAll(modules);
        } else {
            // insert
            info = IConnectorInfo.builder()
                    .connectorId(param.getConnectorId())
                    .status(data.getStatus())
                    .collectStatus(data.getCollectStatus())
                    .version(data.getVersion())
                    .dateTime(param.getDateTime())
                    .modules(modules)
                    .configs(new ArrayList<>())
                    .build();
        }
        configurationRepository.saveAndFlush(info);
    }

    @Override
    public PageVo<IConnectorInfoVo> getConnectorInfos(ClientQueryParam clientQueryParam, PageParam pageParam) {
        Page<IConnectorInfo> page = configurationRepository.findAll(
                PageRequest.of(pageParam.getPageNumber(), pageParam.getPageSize()));
        return ConfigurationMapper.convert2VoPage(page, ConfigurationMapper::mapToVo);
    }

    @Override
    public IConnectorInfoVo updateConnectorInfo(ClientUpdateParam clientUpdateParam) {
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(clientUpdateParam.getConnectorId());
        List<IConnectorConfig> configs = clientUpdateParam.getConfigs().stream().map(t -> IConnectorConfig.builder().id(t.getId()).build()).collect(Collectors.toList());
        if (optional.isPresent()) {
            IConnectorInfo info = optional.get();
            info.getConfigs().clear();
            info.getConfigs().addAll(configs);
            return ConfigurationMapper.mapToVo(info);
        } else {
            throw new RuntimeException("can't find connectorId %s in postgres db");
        }
    }

    @Override
    public void registerConnector(String connectorId) {
        // TODO: transfer message-handler interface
        Optional<IConnectorInfo> optional = configurationRepository.findByConnectorId(connectorId);
        if (optional.isEmpty()) {
            throw new RuntimeException("can't find connectorId %s in postgres db");
        }
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("tenantId", TenantContext.getCurrentTenant());
        params.add("connectorId", connectorId);
        String uri = UriComponentsBuilder.fromUriString("http://localhost:8887/api/v1/iconnector/registration")
                .queryParams(params)
                .toUriString();
        restTemplate.postForEntity(uri, JSON.toJSONString(optional.get()), String.class);
    }
}
