package com.deviniti.multitenancy.separate.schema.controller;

import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorConfig;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorInfo;
import com.deviniti.multitenancy.separate.schema.entity.po.IConnectorModule;
import com.deviniti.multitenancy.separate.schema.service.IConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 3:01 PM
 */
@RestController
@RequestMapping("/api/v1/iconnect")
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

    @GetMapping("/hhh")
    public void test(IConnectorInfo iConnectorInfo, IConnectorConfig connectorConfig, IConnectorModule connectorModule) {
        return;
    }
}
