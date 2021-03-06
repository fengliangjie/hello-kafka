package com.siemens.messagehandler.kafkaconsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.siemens.messagehandler.util.KafkaAclUtil;
import com.siemens.messagehandler.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.siemens.messagehandler.constant.ConstantValus.*;

/**
 * @Author: liangjie.feng
 * @Date: 2022/4/24 17:26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final WebClientUtil configurationServiceWebClientUtil;

    private final WebClientUtil pcfServiceWebClientUtil;

    private final KafkaAclUtil kafkaAclUtil;

    /**
     * consumer of data
     */
    @KafkaListener(topicPattern = TOPIC_PATTERN_DATA, groupId = GROUP_DATA)
    public void iConnectorDataListener(ConsumerRecord<String, String> consumerRecord) {
        // tenantId
        String tenantId = getTenantId(consumerRecord);
        if (!StringUtils.hasText(tenantId)) {
            return;
        }

        // headers
        Map<String, String> headers = new HashMap<>(16);
        headers.put(X_TENANT_ID, tenantId);

        Mono<ResponseEntity<String>> mono = pcfServiceWebClientUtil.post(PCF_SERVICE_DATA_URL, headers, null, JSON.parseObject(consumerRecord.value()));
        mono.subscribe();
    }

    /**
     * consumer of info
     *
     * @param consumerRecord
     */
    @KafkaListener(topicPattern = TOPIC_PATTERN_INFO, groupId = GROUP_INFO)
    public void iConnectInfoListener(ConsumerRecord<String, String> consumerRecord) {
        // tenantId
        String tenantId = getTenantId(consumerRecord);
        if (!StringUtils.hasText(tenantId)) {
            return;
        }

        JSONObject value = JSON.parseObject(consumerRecord.value());

        // createAcl
        createAcl(tenantId, value);

        // headers
        Map<String, String> headers = new HashMap<>(16);
        headers.put(X_TENANT_ID, tenantId);

        Mono<ResponseEntity<String>> mono = configurationServiceWebClientUtil.post(CONFIGURATION_SERVICE_INFO_URL, headers, null, value);
        mono.subscribe();
    }

    public String getTenantId(ConsumerRecord<String, String> consumerRecord) {
        if (consumerRecord == null || !StringUtils.hasText(consumerRecord.value())) {
            log.error("ConsumerRecord is empty!");
            return null;
        }
        String[] topicArr = consumerRecord.topic().split(POINT_TRANSLATE);
        String tenantIdAndConnectorId = topicArr[topicArr.length - 1];
        String[] tenantIdAndConnectorIdArr = tenantIdAndConnectorId.split(DASH);
        String tenantId = tenantIdAndConnectorIdArr[0];
        if (!StringUtils.hasText(tenantId)) {
            log.error("Get tenantId failed!");
            return null;
        }
        return tenantId;
    }

    private void createAcl(String tenantId, JSONObject value) {
        if (!value.containsKey(CONNECTOR_ID) || !value.containsKey(DATA)) {
            log.error("iConnector info absence connectorId or data!");
            return;
        }
        String connectorId = value.getString(CONNECTOR_ID);
        if (!StringUtils.hasText(connectorId)) {
            log.error("ConnectorId is Empty!");
            return;
        }
        if (!value.getJSONObject(DATA).containsKey(COLLECT_STATUS)) {
            log.error("ConnectorId: {}, iConnector info absence collectStatus", connectorId);
            return;
        }
        int collectStatus = value.getJSONObject(DATA).getIntValue(COLLECT_STATUS);
        if (collectStatus == -1) {
            log.info("ConnectorId: {} first time send info data, activate config_topic and data_topic permissions", connectorId);
            kafkaAclUtil.createAcl(String.format(CONFIG_TOPIC, tenantId, connectorId), String.format(PRINCIPAL, tenantId), CONFIG_GROUP);
            kafkaAclUtil.createAcl(String.format(DATA_TOPIC, tenantId, connectorId), String.format(PRINCIPAL, tenantId), DATA_GROUP);
        }
    }
}
