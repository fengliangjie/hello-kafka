package com.siemens.iconnector.controller;

import com.alibaba.fastjson.JSONObject;
import com.siemens.iconnector.kafkaproducer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liangjie.feng
 * @Date: 2022/4/24 17:18
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public String sendJson(String topic, String type, @RequestBody JSONObject data) throws Exception {
        kafkaProducer.send(topic, type, data.toJSONString());
        return "success";
    }
}
