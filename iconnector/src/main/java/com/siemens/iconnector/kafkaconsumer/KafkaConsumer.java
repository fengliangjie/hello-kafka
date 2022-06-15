package com.siemens.iconnector.kafkaconsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: liangjie.feng
 * @date: 2022/6/15 11:02 AM
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "iconnector-config.siemens_001-conId-001", groupId = "group-test")
    public void kafkaListener(ConsumerRecord<String, String> consumerRecord) {
        JSONObject value = JSON.parseObject(consumerRecord.value());

        System.out.println("=====receive configs from message-handler: " + value);
    }
}
