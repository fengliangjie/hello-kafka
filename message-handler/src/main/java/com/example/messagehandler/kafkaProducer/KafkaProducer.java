package com.example.messagehandler.kafkaProducer;

import com.alibaba.fastjson.JSON;
import com.example.messagehandler.entity.Demo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: liangjie.feng
 * @Date: 2022/4/24 17:11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String id, String name) {
        kafkaTemplate.send(topic, JSON.toJSONString(new Demo(id, name)));
        log.info("========send success");
    }

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
        log.info("========send success");
    }
}
