package com.example.messagehandler.configuration;

import com.example.messagehandler.util.KafkaAclUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liangjie.feng
 * @date: 2022/6/10 4:13 PM
 */
@Configuration
public class KafkaAclUtilConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${spring.kafka.security.protocol}")
    private String securityProtocol;

    @Value("${kafka.ssl.location.truststore}")
    private String trustStoreLocation;

    @Value("${kafka.ssl.password}")
    private String trustStorePassword;

    @Value("${kafka.ssl.location.keystore}")
    private String keyStoreLocation;

    @Value("${kafka.ssl.password}")
    private String keyStorePassword;

    @Value("${kafka.ssl.password}")
    private String keyPassword;

    @Bean
    KafkaAclUtil kafkaAclUtil() {
        return new KafkaAclUtil(servers, securityProtocol, trustStoreLocation, trustStorePassword, keyStoreLocation, keyStorePassword, keyPassword);
    }
}
