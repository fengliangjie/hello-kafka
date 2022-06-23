package com.siemens.multitenancy.controller;

import com.siemens.multitenancy.entity.param.*;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;
import com.siemens.multitenancy.service.IConnectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * @param param
     * @return
     */
    @PostMapping("/info")
    public ResponseEntity<String> connectorInfo(@Validated @RequestBody IConnectorInfoParam param) {
        connectService.info(param);
        return ResponseEntity.ok().build();
    }

    /**
     * get iConnector infos
     * @param clientQueryParam
     * @param pageParam
     * @return
     */
    @GetMapping("/getConnectorInfos")
    public ResponseEntity<PageVo<IConnectorInfoVo>> getConnectorInfos(@Validated ClientQueryParam clientQueryParam, @Validated PageParam pageParam) {
        return ResponseEntity.ok(connectService.getConnectorInfos(clientQueryParam, pageParam));
    }

    /**
     * update iConnector info
     * @param clientUpdateParam
     * @return
     */
    @PutMapping("/updateConnectorInfo")
    public ResponseEntity<IConnectorInfoVo> updateConnectorInfo(@Validated @RequestBody ClientUpdateParam clientUpdateParam) {
        return ResponseEntity.ok(connectService.updateConnectorInfo(clientUpdateParam));
    }

    @PostMapping("/uploadConnectorInfo")
    public ResponseEntity<IConnectorInfoVo> uploadConnectorInfo(@Validated ClientUploadParam clientUploadParam) {
        return ResponseEntity.ok(connectService.uploadConnectorInfo(clientUploadParam));
    }

    /**
     * register iConnector
     * @param connectorId
     * @return
     */
    @GetMapping("/register")
    public ResponseEntity<IConnectorInfoVo> registerConnector(@NotBlank(message = "connectorId can't be blank") String connectorId, @NotNull(message = "enable can't be null") boolean enable) {
        return ResponseEntity.ok(connectService.registerConnector(connectorId, enable));
    }

    @GetMapping("/getConnectorInfo")
    public ResponseEntity<IConnectorInfoVo> getConnectorInfo(@NotBlank(message = "connectorId can't be blank") String connectorId) {
        return ResponseEntity.ok(connectService.getConnectorInfo(connectorId));
    }

    @DeleteMapping("/deleteConnector")
    public ResponseEntity<String> deleteConnector(@NotBlank(message = "connectorId can't be blank") String connectorId) {
        connectService.deleteConnector(connectorId);
        return ResponseEntity.ok().build();
    }
}
