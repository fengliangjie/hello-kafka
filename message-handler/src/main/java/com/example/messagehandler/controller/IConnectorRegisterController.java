package com.example.messagehandler.controller;

import com.example.messagehandler.kafkaproducer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.messagehandler.constant.ConstantValus.CONFIG_TOPIC;

/**
 * @author: liangjie.feng
 * @date: 2022/6/8 4:11 PM
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/iconnector")
public class IConnectorRegisterController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/registration")
    public void register(String tenantId, String connectorId, @RequestBody String data) {
        kafkaProducer.send(String.format(CONFIG_TOPIC, tenantId, connectorId), data);
    }
}
