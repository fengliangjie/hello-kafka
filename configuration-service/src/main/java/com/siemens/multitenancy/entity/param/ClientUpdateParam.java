package com.siemens.multitenancy.entity.param;

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
    public static class Config {
        private String name;
    }
}
