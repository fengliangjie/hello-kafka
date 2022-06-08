package com.deviniti.multitenancy.separate.schema.service.impl;

import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;
import com.deviniti.multitenancy.separate.schema.repository.ConfigurationRepository;
import com.deviniti.multitenancy.separate.schema.service.IConnectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//                        .name(param.getName())
//                        .tenantId(param.getTenantId())
//                        .timestamp(Timestamp.valueOf(LocalDateTime.now()))
//                        .build()));
        return null;
    }
}
