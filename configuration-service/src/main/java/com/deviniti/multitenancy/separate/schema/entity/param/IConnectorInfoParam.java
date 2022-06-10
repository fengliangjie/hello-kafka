package com.deviniti.multitenancy.separate.schema.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 2:51 PM
 */
@Data
public class IConnectorInfoParam {
    private String connectorId;
    private Long dateTime;
    private Data data;

    @lombok.Data
    static class Data {
        private Integer status;
        private Integer collectStatus;
        private String version;
        private List<Module> modules;
    }

    @lombok.Data
    static class Module {
        private String name;
        private String version;
        private Integer status;
    }
}
