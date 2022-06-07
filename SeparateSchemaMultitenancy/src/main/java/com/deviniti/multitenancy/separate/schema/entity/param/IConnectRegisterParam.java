package com.deviniti.multitenancy.separate.schema.entity.param;

import lombok.Data;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 2:51 PM
 */
@Data
public class IConnectRegisterParam {
    private String name;
    private String tenantId;
    private String data;
}
