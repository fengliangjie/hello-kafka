package com.deviniti.multitenancy.separate.schema.controller;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientQueryParam;
import com.deviniti.multitenancy.separate.schema.entity.param.ClientUpdateParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorConfig;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorInfo;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorModule;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;
import com.deviniti.multitenancy.separate.schema.service.IConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 3:01 PM
 */
@RestController
@RequestMapping("/api/v1/iconnector")
@RequiredArgsConstructor
public class IConnectController {

    private final IConnectService connectService;

    /**
     * iConnect info
     * @param param
     * @return
     */
    @PostMapping("/info")
    public ResponseEntity<String> connectorInfo(@RequestBody IConnectorInfoParam param) {
        connectService.saveConfiguration(param);
        return ResponseEntity.ok().build();
    }

    /**
     * get iConnector infos
     * @param clientQueryParam
     * @param pageParam
     * @return
     */
    @GetMapping("/getConnectorInfos")
    public ResponseEntity<List<IConnectorInfoVo>> getConnectorInfos(@Validated ClientQueryParam clientQueryParam, @Validated PageParam pageParam) {
        return ResponseEntity.ok(connectService.getConnectorInfos(clientQueryParam, pageParam));
    }

    /**
     * update iConnector info
     * @param clientUpdateParam
     * @return
     */
    @PutMapping("/updateConnectorInfo")
    public ResponseEntity<IConnectorInfoVo> updateConnectorInfo(@RequestBody ClientUpdateParam clientUpdateParam) {
        return ResponseEntity.ok(connectService.updateConnectorInfo(clientUpdateParam));
    }

    /**
     * register iConnector
     * @param connectorId
     * @return
     */
    @GetMapping("/register")
    public ResponseEntity<String> registerConnector(String connectorId) {
        connectService.registerConnector(connectorId);
        return ResponseEntity.ok().build();
    }
}
