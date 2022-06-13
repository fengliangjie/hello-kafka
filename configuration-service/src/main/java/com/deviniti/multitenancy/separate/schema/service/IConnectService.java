package com.deviniti.multitenancy.separate.schema.service;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientQueryParam;
import com.deviniti.multitenancy.separate.schema.entity.param.ClientUpdateParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;

import java.util.List;

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
    List<IConnectorInfoVo> getConnectorInfos(ClientQueryParam clientQueryParam, PageParam pageParam);

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
