package com.deviniti.multitenancy.separate.schema.service.impl;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientQueryParam;
import com.deviniti.multitenancy.separate.schema.entity.param.ClientUpdateParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorInfo;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;
import com.deviniti.multitenancy.separate.schema.repository.ConfigurationRepository;
import com.deviniti.multitenancy.separate.schema.service.IConnectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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

    @Override
    public IConnectorInfoVo saveConfiguration(IConnectorInfoParam param) {
//        return ConfigurationMapper.mapToVo(configurationRepository.save(
//                IConnectorInfo.builder()
//                        .connectorId(param.getConnectorId())
//                        .status(param.getData().getStatus())
//                        .collectStatus(param.getData().getCollectStatus())
//                        .version(param.getData().getVersion())
//                        .dateTime(param.getDateTime())
//                        .modules()
        return null;
    }

    @Override
    public List<IConnectorInfoVo> getConnectorInfos(ClientQueryParam clientQueryParam, PageParam pageParam) {
//        return configurationRepository.findAll(
//                Example.of(IConnectorInfo.builder().tenantId(clientParam.getTenantId()).build()), PageRequest.of(pageParam.getPageNumber(), pageParam.getPageSize()))
//                .stream()
//                .map(ConfigurationMapper::mapToVo)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public IConnectorInfoVo updateConnectorInfo(ClientUpdateParam clientUpdateParam) {
        //Optional<IConnectorInfo> configuration = configurationRepository.findById(configurationParam.getId());
        // TODO: update DB
        return null;
    }

    @Override
    public void registerConnector(String connectorId) {
        // TODO: transfer message-handler interface
    }
}
