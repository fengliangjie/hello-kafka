package com.example.messagehandler.kafkaConsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.messagehandler.util.KafkaAclUtil;
import com.example.messagehandler.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @Author: liangjie.feng
 * @Date: 2022/4/24 17:26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final WebClientUtil webClientUtil;

    private final KafkaAclUtil kafkaAclUtil;

    private static final String CONFIG_TOPIC = "iconnector-config.%s-%s";

    private static final String DATA_TOPIC = "iconnector-data.%s-%s";

    private static final String CONFIG_GROUP = "group-config";

    private static final String DATA_GROUP = "group-data";

    private static final String PRINCIPAL = "User:CN=%s";

    /**
     * consumer of data
     *
     * insert or update db
     *
     * 正则表达式动态监听多个topic: topicPattern = "siemens_test.*"
     * @param consumerRecord
     */
    @KafkaListener(topicPattern = "iconnector-data.*", groupId = "group-data")
    public void kafkaListener(ConsumerRecord<String, String> consumerRecord) {
        String value = consumerRecord.value();
        System.out.println("=====data: " + value);
//        JSONObject jsonObject = JSON.parseObject(value);
//        String tenantId = jsonObject.getString("tenantId");
//
//        // headers
//        Map<String, String> headers = new HashMap<>();
//        headers.put("tenantId", tenantId);
//
//        Mono<ResponseEntity<String>> mono = webClientUtil.post("http://", headers, null, jsonObject);
//        mono.subscribe(responseEntity -> {
//            String body = responseEntity.getBody();
//            System.out.println("response:" + body);
//        });
    }

    /**
     * consumer of registration
     *
     * insert into db
     *
     * @param consumerRecord
     */
    @KafkaListener(topicPattern = "iconnector-info.*", groupId = "group-info")
    public void iConnectRegistrationListener(ConsumerRecord<String, String> consumerRecord) throws ExecutionException, InterruptedException {
        JSONObject value = JSON.parseObject(consumerRecord.value());
        String topic = consumerRecord.topic();
        String[] topicArr = topic.split("\\.");
        String tenantId = topicArr[topicArr.length - 1];
        String connectorId = value.getString("connectorId");
        int collectStatus = value.getJSONObject("data").getIntValue("collectStatus");
        System.out.println("=====receive message=====");
        if (collectStatus == -1) {
            System.out.println("=====create acl=====");
            kafkaAclUtil.createAcl(String.format(CONFIG_TOPIC, tenantId, connectorId), String.format(PRINCIPAL, tenantId), CONFIG_GROUP);
            kafkaAclUtil.createAcl(String.format(DATA_TOPIC, tenantId, connectorId), String.format(PRINCIPAL, tenantId), DATA_GROUP);
        }

        // headers
//        Map<String, String> headers = new HashMap<>();
//        headers.put("tenantId", tenantId);
//
//        Mono<ResponseEntity<String>> mono = webClientUtil.post("http://localhost:8886", headers, null, value);
//        mono.subscribe(responseEntity -> {
//            String body = responseEntity.getBody();
//            System.out.println("=====result: " + body);
//        });
    }
}
