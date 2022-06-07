package com.deviniti.multitenancy.separate.schema.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 5:14 PM
 */
@Data
@Builder
public class ConfigurationDto {
    private Long id;
    private String name;
    private String tenantId;
    private Timestamp timestamp;
}
