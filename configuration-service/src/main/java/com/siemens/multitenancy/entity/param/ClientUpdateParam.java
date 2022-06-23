package com.siemens.multitenancy.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/23 13:29
 */
@Data
public class ClientUpdateParam {
    @NotNull(message = "configs can't be null")
    private List<IConnectorConfigParam> configs;
}
