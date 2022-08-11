package com.siemens.iconnector.kafkaproducer;

import com.siemens.iconnector.util.JwtUtils;
import com.siemens.iconnector.util.RsaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @Author: liangjie.feng
 * @Date: 2022/4/24 17:11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String type, String message) throws Exception {
        if (type.equals("jws")) {
            System.out.println("=====send jws message");
            kafkaTemplate.send(new ProducerRecord<>(topic,
                    null,
                    null,
                    null,
                    JwtUtils.generateTokenExpireInMinutes(message, RsaUtils.getPrivateKey("/certs/jwt/ca-key"), -1),
                    Collections.singleton(new RecordHeader("Content-Type", "application/jose".getBytes(StandardCharsets.UTF_8)))));
        } else {
            System.out.println("=====send json message");
            kafkaTemplate.send(topic, message);
        }
        log.info("========send success");
    }
}
