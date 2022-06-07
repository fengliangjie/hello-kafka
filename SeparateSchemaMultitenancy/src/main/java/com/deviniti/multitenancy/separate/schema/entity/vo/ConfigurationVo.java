package com.deviniti.multitenancy.separate.schema.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 5:35 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationVo {
    /**
     * id
     */
    private Long id;
    /**
     * name
     */
    private String name;
    /**
     * tenantId
     */
    private String tenantId;
    /**
     * timestamp
     */
    private Timestamp timestamp;
}
