package com.deviniti.multitenancy.separate.schema.controller;

import com.deviniti.multitenancy.separate.schema.entity.param.ClientParam;
import com.deviniti.multitenancy.separate.schema.entity.param.ConfigurationParam;
import com.deviniti.multitenancy.separate.schema.entity.param.PageParam;
import com.deviniti.multitenancy.separate.schema.entity.vo.ConfigurationVo;
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
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    /**
     * get configurations
     * @param clientParam
     * @param pageParam
     * @return
     */
    @GetMapping("/getConfigurations")
    public ResponseEntity<List<ConfigurationVo>> getConfigurations(@Validated ClientParam clientParam, @Validated PageParam pageParam) {
        return ResponseEntity.ok(clientService.getConfigurations(clientParam, pageParam));
    }

    /**
     * update configuration
     * @param configurationParam
     * @return
     */
    @PutMapping("/updateConfiguration")
    public ResponseEntity<ConfigurationVo> updateConfiguration(@RequestBody ConfigurationParam configurationParam) {
        return ResponseEntity.ok(clientService.updateConfiguration(configurationParam));
    }
}
