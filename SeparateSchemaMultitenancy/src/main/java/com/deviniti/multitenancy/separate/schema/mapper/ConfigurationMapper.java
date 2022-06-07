package com.deviniti.multitenancy.separate.schema.mapper;

import com.deviniti.multitenancy.separate.schema.entity.dto.ConfigurationDto;
import com.deviniti.multitenancy.separate.schema.entity.po.Configuration;
import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 5:20 PM
 */
public class ConfigurationMapper {
    public static ConfigurationDto mapToDto(Configuration configuration) {
        return ConfigurationDto.builder()
                .id(configuration.getId())
                .name(configuration.getName())
                .tenantId(configuration.getTenantId())
                .timestamp(configuration.getTimestamp())
                .build();
    }

    public static ConfigurationVo mapToVo(Configuration configuration) {
        return ConfigurationVo.builder()
                .id(configuration.getId())
                .name(configuration.getName())
                .tenantId(configuration.getTenantId())
                .timestamp(configuration.getTimestamp())
                .build();
    }

    public static ConfigurationVo mapToVo(ConfigurationDto configurationDto) {
        return ConfigurationVo.builder()
                .id(configurationDto.getId())
                .name(configurationDto.getName())
                .tenantId(configurationDto.getTenantId())
                .timestamp(configurationDto.getTimestamp())
                .build();
    }
}
