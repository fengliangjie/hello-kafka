package com.deviniti.multitenancy.separate.schema.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liangjie.feng
 * @Date: 2022/5/12 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo {
    private String id;
    private String name;
    private String tenantId;
}
