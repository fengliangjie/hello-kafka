package com.deviniti.multitenancy.separate.schema.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 6:13 PM
 */
@Data
@ApiModel("client param")
public class ClientParam {
    private Long id;
    private String name;
    @ApiModelProperty(name = "tenant_id")
    private String tenantId;
}
