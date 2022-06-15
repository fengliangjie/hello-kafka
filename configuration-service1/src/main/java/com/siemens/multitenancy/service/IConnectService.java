package com.siemens.multitenancy.service;

import com.siemens.multitenancy.entity.param.ClientQueryParam;
import com.siemens.multitenancy.entity.param.ClientUpdateParam;
import com.siemens.multitenancy.entity.param.IConnectorInfoParam;
import com.siemens.multitenancy.entity.param.PageParam;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;

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
    void info(IConnectorInfoParam param);

    /**
     * get iConnector infos
     * @param clientQueryParam
     * @param pageParam
     * @return
     */
    PageVo<IConnectorInfoVo> getConnectorInfos(ClientQueryParam clientQueryParam, PageParam pageParam);

    /**
     * update iConnector info
     * @param clientUpdateParam
     * @return
     */
    IConnectorInfoVo updateConnectorInfo(ClientUpdateParam clientUpdateParam);

    /**
     * register iConnector
     * @param connectorId
     */
    void registerConnector(String connectorId);
}
