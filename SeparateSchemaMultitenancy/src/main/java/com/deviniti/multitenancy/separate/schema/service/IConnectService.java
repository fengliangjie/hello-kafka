package com.deviniti.multitenancy.separate.schema.service;

import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 4:00 PM
 */
public interface IConnectService {
    /**
     * save iConnect configuration
     * @param param
     * @return iConnect configuration
     */
    IConnectorInfoVo saveConfiguration(IConnectorInfoParam param);
}
