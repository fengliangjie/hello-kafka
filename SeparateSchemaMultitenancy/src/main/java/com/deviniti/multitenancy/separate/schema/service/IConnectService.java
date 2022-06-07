package com.deviniti.multitenancy.separate.schema.service;

import com.deviniti.multitenancy.separate.schema.entity.param.IConnectPingParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectRegisterParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;

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
    ConfigurationVo saveConfiguration(IConnectRegisterParam param);

    /**
     * update timestamp of healthy monitoring
     * @param param
     */
    void ping(IConnectPingParam param);
}
