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
import java.util.HashMap;
import java.util.Properties;

import static com.siemens.iconnector.constant.ConstantValus.*;

/**
 * @Author: liangjie.feng
 * @Date: 2022/4/24 17:11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Properties properties;

    public void send(String topic, String type, String message) throws Exception {
        if (type.equals("jws")) {
            System.out.println("=====send jws message");
            kafkaTemplate.send(new ProducerRecord<>(
                    topic,
                    null,
                    null,
                    null,
                    JwtUtils.generateTokenExpireInMinutes(message, RsaUtils.getPrivateKey(properties.getProperty(JWT_PRIVATE_KEY)),
                            new HashMap<>(1) {
                        {
                            put(SIGNER_CERTIFICATE, new String(RsaUtils.readFile(properties.getProperty(JWT_CERTIFICATE))));
                        }
                    }),
                    Collections.singleton(new RecordHeader("Content-Type", "application/jose".getBytes(StandardCharsets.UTF_8)))));
        } else {
            System.out.println("=====send json message");
            kafkaTemplate.send(topic, message);
        }
        log.info("========send success");
    }
}
