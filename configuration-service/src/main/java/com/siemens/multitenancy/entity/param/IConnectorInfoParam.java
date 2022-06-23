package com.siemens.multitenancy.entity.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 2:51 PM
 */
@Data
public class IConnectorInfoParam {

    @NotBlank(message = "connectorId can't be blank")
    private String connectorId;
    @NotNull(message = "dateTime can't be null")
    private Long dateTime;
    @NotNull(message = "data can't be null")
    private Data data;

    @lombok.Data
    public static class Data {
        private Integer status;
        private Integer collectStatus;
        private String version;
        private List<Module> modules;
    }

    @lombok.Data
    public static class Module {
        private String name;
        private String version;
        private Integer status;
    }
}
