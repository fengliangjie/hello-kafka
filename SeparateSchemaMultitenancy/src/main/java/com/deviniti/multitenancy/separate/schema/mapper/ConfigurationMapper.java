//package com.deviniti.multitenancy.separate.schema.mapper;
//
//import com.deviniti.multitenancy.separate.schema.entity.dto.ConfigurationDto;
//import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorInfo;
//import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;
//
///**
// * @author: liangjie.feng
// * @date: 2022/5/31 5:20 PM
// */
//public class ConfigurationMapper {
//    public static ConfigurationDto mapToDto(IConnectorInfo IConnectorInfo) {
//        return ConfigurationDto.builder()
//                .id(IConnectorInfo.getId())
//                .name(IConnectorInfo.getName())
//                .tenantId(IConnectorInfo.getTenantId())
//                .timestamp(IConnectorInfo.getTimestamp())
//                .build();
//    }
//
//    public static ConfigurationVo mapToVo(IConnectorInfo IConnectorInfo) {
//        return ConfigurationVo.builder()
//                .id(IConnectorInfo.getId())
//                .name(IConnectorInfo.getName())
//                .tenantId(IConnectorInfo.getTenantId())
//                .timestamp(IConnectorInfo.getTimestamp())
//                .build();
//    }
//
//    public static ConfigurationVo mapToVo(ConfigurationDto configurationDto) {
//        return ConfigurationVo.builder()
//                .id(configurationDto.getId())
//                .name(configurationDto.getName())
//                .tenantId(configurationDto.getTenantId())
//                .timestamp(configurationDto.getTimestamp())
//                .build();
//    }
//}
