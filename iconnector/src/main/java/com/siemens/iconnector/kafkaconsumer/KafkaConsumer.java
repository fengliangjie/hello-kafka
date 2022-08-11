package com.siemens.iconnector.kafkaconsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.siemens.iconnector.util.JwtUtils;
import com.siemens.iconnector.util.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Iterator;

import static com.siemens.iconnector.constant.ConstantValus.*;

/**
 * @author: liangjie.feng
 * @date: 2022/6/15 11:02 AM
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "iconnector-config.dec-conId_001", groupId = "group-config")
    public void kafkaListener(ConsumerRecord<String, String> consumerRecord) throws Exception {
        JSONObject value = JSON.parseObject(getValue(consumerRecord));

        System.out.println("=====receive configs from message-handler: " + value);
    }

    private String getValue(ConsumerRecord<String, String> consumerRecord) throws Exception {
        Iterator<Header> iterator = consumerRecord.headers().headers(CONTENT_TYPE).iterator();
        if (iterator.hasNext() && new String(iterator.next().value()).equals(APPLICATION_JOSE)) {
            System.out.println("=====解析jws");
            return JwtUtils.getMessageFromToken(consumerRecord.value(), RsaUtils.getPublicKey("/certs/jwt/ca-key-pub"));
        } else {
            System.out.println("=====解析json");
            return consumerRecord.value();
        }
    }
}
