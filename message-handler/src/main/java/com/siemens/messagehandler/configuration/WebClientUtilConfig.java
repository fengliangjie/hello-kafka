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
    private int maxInMemorySize = 10 * 1024 * 1024;

    @Value("${webclient.connectTimeout:3000}")
    private int connectTimeout = 3000;

    @Value("${webclient.responseTimeout:3000}")
    private int readTimeout = 3000;

    @Value("${webclient.maxConnections:1000}")
    private int maxConnections = 1000;

    @Value("${webclient.maxPendingCount:3000}")
    private int maxPendingCount = 3000;

    @Value("${configuration-service.ssl-client.client-file}")
    private String configurationServiceSslFilePath;

    @Value("${configuration-service.ssl-client.client-type}")
    private String configurationServiceSslFileType;

    @Value("${configuration-service.ssl-client.client-password}")
    private String configurationServiceSslPassword;

    @Value("${pcf-service.ssl-client.client-file}")
    private String pcfServiceSslFilePath;

    @Value("${pcf-service.ssl-client.client-type}")
    private String pcfServiceSslFileType;

    @Value("${pcf-service.ssl-client.client-password}")
    private String pcfServiceSslPassword;

    @Bean(name = "configurationServiceWebClientUtil")
    WebClientUtil configurationServiceWebClientUtil() throws Exception {
        return new WebClientUtil(maxInMemorySize, connectTimeout, readTimeout, maxConnections, maxPendingCount, configurationServiceSslFilePath, configurationServiceSslFileType, configurationServiceSslPassword);
    }

    @Bean(name = "pcfServiceWebClientUtil")
    WebClientUtil pcfServiceWebClientUtil() throws Exception {
        return new WebClientUtil(maxInMemorySize, connectTimeout, readTimeout, maxConnections, maxPendingCount, pcfServiceSslFilePath, pcfServiceSslFileType, pcfServiceSslPassword);
    }
}
