package com.example.messagehandler.kafkaConsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.messagehandler.util.KafkaAclUtil;
import com.example.messagehandler.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.example.messagehandler.constant.ConstantValus.*;

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

    /**
     * consumer of data
     *
     * @param consumerRecord
     */
    @KafkaListener(topicPattern = "iconnector-data.*", groupId = "group-data")
    public void kafkaListener(ConsumerRecord<String, String> consumerRecord) {
        JSONObject value = JSON.parseObject(consumerRecord.value());

        // tenantId
        String tenantId = getTenantId(consumerRecord.topic());

        // headers
        Map<String, String> headers = new HashMap<>();
        headers.put("X-TENANT-ID", tenantId);

        Mono<ResponseEntity<String>> mono = webClientUtil.post("http://", headers, null, value);
        mono.subscribe();
    }

    /**
     * consumer of info
     *
     * @param consumerRecord
     */
    @KafkaListener(topicPattern = "iconnector-info.*", groupId = "group-info")
    public void iConnectRegistrationListener(ConsumerRecord<String, String> consumerRecord) {
        JSONObject value = JSON.parseObject(consumerRecord.value());

        // tenantId
        String tenantId = getTenantId(consumerRecord.topic());

        // createAcl
        //createAcl(tenantId, value);

        // headers
        Map<String, String> headers = new HashMap<>();
        headers.put("X-TENANT-ID", tenantId);

        Mono<ResponseEntity<String>> mono = webClientUtil.post("http://localhost:8886/api/v1/iconnector/info", headers, null, value);
        mono.subscribe();
    }

    private String getTenantId(String topic) {
        String[] topicArr = topic.split("\\.");
        String tenantIdAndConnectorId = topicArr[topicArr.length - 1];
        String[] tenantIdAndConnectorIdArr = tenantIdAndConnectorId.split("-");
        return tenantIdAndConnectorIdArr[0];
    }

    private void createAcl(String tenantId, JSONObject value) {
        String connectorId = value.getString("connectorId");
        int collectStatus = value.getJSONObject("data").getIntValue("collectStatus");
        if (collectStatus == -1) {
            kafkaAclUtil.createAcl(String.format(CONFIG_TOPIC, tenantId, connectorId), String.format(PRINCIPAL, tenantId), CONFIG_GROUP);
            kafkaAclUtil.createAcl(String.format(DATA_TOPIC, tenantId, connectorId), String.format(PRINCIPAL, tenantId), DATA_GROUP);
        }
    }
}
