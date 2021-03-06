package com.siemens.multitenancy.entity.param;

import lombok.Data;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 6:13 PM
 */
@Data
public class ClientQueryParam {

    private Long id;

    private String name;

    private String tenantId;
}
