package com.siemens.messagehandler.configuration;

import com.siemens.messagehandler.util.WebClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liangjie.feng
 * @version 1.0
 * @date 2022/5/25 下午3:32
 * @description
 */
@Configuration
public class WebClientUtilConfig {

    @Value("${webclient.maxInMemorySize:10485760}")
    int maxInMemorySize = 10 * 1024 * 1024;

    @Value("${webclient.connectTimeout:3000}")
    int connectTimeout = 3000;

    @Value("${webclient.responseTimeout:3000}")
    int readTimeout = 3000;

    @Value("${webclient.maxConnections:1000}")
    int maxConnections = 1000;

    @Value("${webclient.maxPendingCount:3000}")
    int maxPendingCount = 3000;

    @Bean
    WebClientUtil webClientUtil() {
        return new WebClientUtil(maxInMemorySize, connectTimeout, readTimeout, maxConnections, maxPendingCount);
    }
}
