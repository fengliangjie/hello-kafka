package com.siemens.multitenancy.controller;

import com.siemens.multitenancy.entity.param.*;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;
import com.siemens.multitenancy.service.IConnectorService;
import com.siemens.pcf.common.entity.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 3:01 PM
 */
@Validated
@RestController
@RequestMapping("/api/v1/iconnector")
@RequiredArgsConstructor
public class IConnectorController {

    private final IConnectorService connectService;

    /**
     * iConnect info
     *
     * @param param
     * @return
     */
    @PostMapping("/info")
    public ResponseResult<String> connectorInfo(@Validated @RequestBody IConnectorInfoParam param) {
        connectService.info(param);
        return ResponseResult.success();
    }

    /**
     * get iConnector infos
     *
     * @param clientQueryParam
     * @param pageParam
     * @return
     */
    @GetMapping("/getConnectorInfos")
    public ResponseResult<PageVo<IConnectorInfoVo>> getConnectorInfos(@Validated ClientQueryParam clientQueryParam, @Validated PageParam pageParam) {
        return ResponseResult.success(connectService.getConnectorInfos(clientQueryParam, pageParam));
    }

    /**
     * update iConnector info
     *
     * @param connectorId
     * @param clientUpdateParam
     * @return
     */
    @PutMapping("/updateConnectorInfo")
    public ResponseResult<IConnectorInfoVo> updateConnectorInfo(@NotBlank(message = "connectorId can't be blank") String connectorId, @Validated @RequestBody ClientUpdateParam clientUpdateParam) {
        return ResponseResult.success(connectService.updateConnectorInfo(connectorId, clientUpdateParam.getConfigs()));
    }

    /**
     * upload iConnector info
     *
     * @param clientUploadParam
     * @return
     */
    @PostMapping("/uploadConnectorInfo")
    public ResponseResult<IConnectorInfoVo> uploadConnectorInfo(@Validated ClientUploadParam clientUploadParam) {
        return ResponseResult.success(connectService.uploadConnectorInfo(clientUploadParam));
    }

    /**
     * register iConnector
     *
     * @param connectorId
     * @return
     */
    @GetMapping("/register")
    public ResponseResult<IConnectorInfoVo> registerConnector(@NotBlank(message = "connectorId can't be blank") String connectorId, @NotNull(message = "enable can't be null") boolean enable) {
        return ResponseResult.success(connectService.registerConnector(connectorId, enable));
    }

    /**
     * get iConnector info
     *
     * @param connectorId
     * @return
     */
    @GetMapping("/getConnectorInfo")
    public ResponseResult<IConnectorInfoVo> getConnectorInfo(@NotBlank(message = "connectorId can't be blank") String connectorId) {
        return ResponseResult.success(connectService.getConnectorInfo(connectorId));
    }

    /**
     * delete iConnector info
     *
     * @param connectorId
     * @return
     */
    @DeleteMapping("/deleteConnector")
    public ResponseResult<String> deleteConnector(@NotBlank(message = "connectorId can't be blank") String connectorId) {
        connectService.deleteConnector(connectorId);
        return ResponseResult.success();
    }
}
