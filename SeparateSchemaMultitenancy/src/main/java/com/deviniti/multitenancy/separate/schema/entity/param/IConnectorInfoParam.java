package com.deviniti.multitenancy.separate.schema.entity.param;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 2:51 PM
 */
@Data
public class IConnectorInfoParam {
    private String connectorId;
    private LocalDateTime dateTime;
    private Data data;

    @lombok.Data
    class Data {
        private Integer status;
        private Integer collectStatus;
        private String version;
        private List<Module> modules;
    }

    @lombok.Data
    class Module {
        String name;
        String version;
        Integer status;
    }
}
