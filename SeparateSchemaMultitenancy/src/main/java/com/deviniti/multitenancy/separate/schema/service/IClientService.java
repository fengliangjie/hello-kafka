package com.deviniti.multitenancy.separate.schema.service;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;

import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 4:01 PM
 */
public interface IClientService {

    /**
     * get iConnector infos
     * @param clientParam
     * @param pageParam
     * @return
     */
    List<IConnectorInfoVo> getConnectorInfos(ClientParam clientParam, PageParam pageParam);

    /**
     * update iConnector info
     * @param connectorInfoParam
     * @return
     */
    IConnectorInfoVo updateConnectorInfo(IConnectorInfoParam connectorInfoParam);

    /**
     * register iConnector
     * @param connectorId
     */
    void registerConnector(String connectorId);
}
