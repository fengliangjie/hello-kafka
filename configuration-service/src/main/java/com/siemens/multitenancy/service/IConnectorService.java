package com.siemens.multitenancy.service;

import com.siemens.multitenancy.entity.param.*;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 4:00 PM
 */
public interface IConnectorService {

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
     * upload iConnector info
     * @param clientUploadParam
     * @return
     */
    IConnectorInfoVo uploadConnectorInfo(ClientUploadParam clientUploadParam);

    /**
     * register iConnector
     * @param connectorId
     * @param enable
     * @return
     */
    IConnectorInfoVo registerConnector(String connectorId, boolean enable);

    /**
     * get iConnector info by connectorId
     * @param connectorId
     * @return
     */
    IConnectorInfoVo getConnectorInfo(String connectorId);

    /**
     * delete by connectorId
     * @param connectorId
     */
    void deleteConnector(String connectorId);
}
