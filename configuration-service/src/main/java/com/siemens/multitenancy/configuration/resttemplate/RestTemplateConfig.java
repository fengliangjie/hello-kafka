package com.siemens.multitenancy.configuration.resttemplate;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContextBuilder;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/14 6:00 PM
 */
@Configuration
public class RestTemplateConfig {

    @Value("${message-handler.ssl-client.client-file}")
    private String sslFilePath;

    @Value("${message-handler.ssl-client.client-type}")
    private String sslFileType;

    @Value("${message-handler.ssl-client.client-password}")
    private String sslPassword;

    @Bean
    public RestTemplate restTemplateSsl() throws Exception {
        if (!StringUtils.hasText(sslFilePath) || !StringUtils.hasText(sslFileType) || !StringUtils.hasText(sslPassword)) {
            throw new Exception("client certificate absent, please check!");
        }
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        HttpComponentsClientHttpRequestFactory factory = new
                HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5 * 60 * 1000);
        factory.setConnectTimeout(5 * 60 * 1000);
        factory.setReadTimeout(5 * 60 * 1000);
        SSLContextBuilder builder = new SSLContextBuilder();
        KeyStore keyStore = KeyStore.getInstance(sslFileType);
        File file = new File(sslFilePath);
        if (!file.exists()) {
            throw new Exception("can't find client-file!");
        }
        InputStream inputStream = new FileInputStream(sslFilePath);
        keyStore.load(inputStream, sslPassword.toCharArray());
        builder.loadKeyMaterial(keyStore, sslPassword.toCharArray());
        builder.loadTrustMaterial(keyStore, null);
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager(registry);
        phccm.setMaxTotal(200);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).setConnectionManager(phccm).setConnectionManagerShared(true).build();
        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        ArrayList<HttpMessageConverter<?>> convertersValid = new ArrayList<>();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter ||
                    converter instanceof MappingJackson2XmlHttpMessageConverter) {
                continue;
            }
            convertersValid.add(converter);
        }
        convertersValid.add(httpMessageConverter);
        restTemplate.setMessageConverters(convertersValid);
        inputStream.close();
        return restTemplate;
    }
}
