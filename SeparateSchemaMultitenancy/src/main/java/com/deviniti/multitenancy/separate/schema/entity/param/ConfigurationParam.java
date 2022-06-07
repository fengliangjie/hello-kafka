package com.deviniti.multitenancy.separate.schema.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: liangjie.feng
 * @date: 2022/6/6 10:28 AM
 */
@Data
@ApiModel("configuration param")
public class ConfigurationParam {
    private Long id;
    @ApiModelProperty(name = "tenant_id")
    private String tenantId;
    private String data;
}
