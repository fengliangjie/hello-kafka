package com.deviniti.multitenancy.separate.schema.service.impl;

import com.deviniti.multitenancy.separate.schema.entity.param.IConnectPingParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectRegisterParam;
import com.deviniti.multitenancy.separate.schema.entity.po.Configuration;
import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;
import com.deviniti.multitenancy.separate.schema.mapper.ConfigurationMapper;
import com.deviniti.multitenancy.separate.schema.repository.ConfigurationRepository;
import com.deviniti.multitenancy.separate.schema.service.IConnectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Override
    public ConfigurationVo saveConfiguration(IConnectRegisterParam param) {
        return ConfigurationMapper.mapToVo(configurationRepository.save(
                Configuration.builder()
                        .name(param.getName())
                        .tenantId(param.getTenantId())
                        .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                        .build()));
    }

    @Override
    public void ping(IConnectPingParam param) {
        Optional<Configuration> configuration =  configurationRepository.findById(param.getId());
        configuration.ifPresent(value -> value.setTimestamp(param.getTimestamp()));
    }
}
