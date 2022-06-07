package com.deviniti.multitenancy.separate.schema.service.impl;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientParam;
import com.deviniti.multitenancy.separate.schema.entity.param.ConfigurationParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.po.Configuration;
import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;
import com.deviniti.multitenancy.separate.schema.mapper.ConfigurationMapper;
import com.deviniti.multitenancy.separate.schema.repository.ConfigurationRepository;
import com.deviniti.multitenancy.separate.schema.service.IClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ClientServiceImpl implements IClientService {
    private final ConfigurationRepository configurationRepository;

    @Override
    public List<ConfigurationVo> getConfigurations(ClientParam clientParam, PageParam pageParam) {
        return configurationRepository.findAll(
                Example.of(Configuration.builder().tenantId(clientParam.getTenantId()).build()), PageRequest.of(pageParam.getPageNumber(), pageParam.getPageSize()))
                .stream()
                .map(ConfigurationMapper::mapToVo)
                .collect(Collectors.toList());
    }

    @Override
    public ConfigurationVo updateConfiguration(ConfigurationParam configurationParam) {
        Optional<Configuration> configuration =  configurationRepository.findById(configurationParam.getId());
        // TODO: update DB
        // TODO: transfer message-handler interface
        return null;
    }
}
