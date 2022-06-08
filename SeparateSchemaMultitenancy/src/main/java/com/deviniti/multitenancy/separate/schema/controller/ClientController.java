package com.deviniti.multitenancy.separate.schema.controller;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectorInfoParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.IConnectorInfoVo;
import com.deviniti.multitenancy.separate.schema.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 3:52 PM
 */
@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    /**
     * get iConnector infos
     * @param clientParam
     * @param pageParam
     * @return
     */
    @GetMapping("/getConnectorInfos")
    public ResponseEntity<List<IConnectorInfoVo>> getConnectorInfos(@Validated ClientParam clientParam, @Validated PageParam pageParam) {
        return ResponseEntity.ok(clientService.getConnectorInfos(clientParam, pageParam));
    }

    /**
     * update iConnector info
     * @param connectorInfoParam
     * @return
     */
    @PutMapping("/updateConnectorInfo")
    public ResponseEntity<IConnectorInfoVo> updateConnectorInfo(@RequestBody IConnectorInfoParam connectorInfoParam) {
        return ResponseEntity.ok(clientService.updateConnectorInfo(connectorInfoParam));
    }

    /**
     * register iConnector
     * @param connectorId
     * @return
     */
    @GetMapping("/register")
    public ResponseEntity<String> registerConnector(String connectorId) {
        clientService.registerConnector(connectorId);
        return ResponseEntity.ok().build();
    }
}
