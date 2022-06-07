package com.example.messagehandler.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.messagehandler.kafkaProducer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/send")
    public String send(String topic, String id, String name) {
        kafkaProducer.send(topic, id, name);
        return "success";
    }

    @PostMapping("/send")
    public String sendJson(String topic, @RequestBody JSONObject data) {
        kafkaProducer.send(topic, data.toJSONString());
        return "success";
    }
}
