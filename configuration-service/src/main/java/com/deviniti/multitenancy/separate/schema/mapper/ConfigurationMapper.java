package com.deviniti.multitenancy.separate.schema.mapper;

import com.deviniti.multitenancy.separate.schema.entity.dto.ConfigurationDto;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorInfo;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 5:20 PM
 */
public class ConfigurationMapper {

    public static IConnectorInfoVo mapToVo(IConnectorInfo connectorInfo) {
        LocalDateTime localDateTime = Instant.ofEpochSecond(connectorInfo.getDateTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return IConnectorInfoVo.builder()
                .id(connectorInfo.getId())
                .connectorId(connectorInfo.getConnectorId())
                .status(connectorInfo.getStatus())
                .collectStatus(connectorInfo.getCollectStatus())
                .version(connectorInfo.getVersion())
                .dateTime(localDateTime)
                .modules(connectorInfo.getModules())
                .configs(connectorInfo.getConfigs())
                .build();
    }
}
