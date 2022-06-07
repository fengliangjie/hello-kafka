package com.deviniti.multitenancy.separate.schema.controller;

import com.deviniti.multitenancy.separate.schema.entity.param.IConnectPingParam;
import com.deviniti.multitenancy.separate.schema.entity.param.IConnectRegisterParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;
import com.deviniti.multitenancy.separate.schema.service.IConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 3:01 PM
 */
@RestController
@RequestMapping("/api/iconnect")
@RequiredArgsConstructor
public class IConnectController {

    private final IConnectService connectService;

    /**
     * register iConnect
     * @param param
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<ConfigurationVo> registerIConnect(@RequestBody IConnectRegisterParam param) {
        return ResponseEntity.ok(connectService.saveConfiguration(param));
    }

    /**
     * update timestamp of healthy monitoring
     * @param param
     * @return
     */
    @PutMapping("/ping")
    public ResponseEntity<String> iConnectPing(IConnectPingParam param) {
        connectService.ping(param);
        return ResponseEntity.ok().build();
    }
}
