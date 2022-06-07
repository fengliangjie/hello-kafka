package com.deviniti.multitenancy.separate.schema.service;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientParam;
import com.deviniti.multitenancy.separate.schema.entity.param.ConfigurationParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;

import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 4:01 PM
 */
public interface IClientService {
    /**
     * get configurations of given tenantId
     * @param clientParam
     * @param pageParam
     * @return
     */
    List<ConfigurationVo> getConfigurations(ClientParam clientParam, PageParam pageParam);

    ConfigurationVo updateConfiguration(ConfigurationParam configurationParam);
}
