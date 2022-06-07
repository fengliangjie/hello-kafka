package com.deviniti.multitenancy.separate.schema.entity.param;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 2:58 PM
 */
@Data
public class IConnectPingParam {
    private Long id;
    private Timestamp timestamp;
}
