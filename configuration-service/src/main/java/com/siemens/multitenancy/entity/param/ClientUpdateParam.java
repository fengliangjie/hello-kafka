package com.siemens.multitenancy.entity.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/8 3:11 PM
 */
@Data
public class ClientUpdateParam {

    @NotBlank(message = "connectorId can't be blank")
    private String connectorId;
    @NotNull(message = "configs can't be null")
    private List<Config> configs;

    @lombok.Data
    public static class Config {
        private String name;
    }
}
