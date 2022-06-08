package com.deviniti.multitenancy.separate.schema.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/8 3:11 PM
 */
@Data
public class ClientUpdateParam {

    private String connectorId;
    private List<Config> configs;

    @lombok.Data
    static class Config {
    }
}