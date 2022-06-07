package com.example.messagehandler.util;

import com.google.common.collect.ImmutableList;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author liangjie.feng
 * @version 1.0
 * @date 2022/5/25 下午2:37
 * @description The WebClient tool class, which can be used for simple get/post requests, supports thread pooling, timeout configuration
 */
@Data
public class WebClientUtil {

    private WebClient webClient;

    private List<HttpMethod> supports = ImmutableList.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.HEAD);

    /**
     * Codecs have limits for buffering data in memory to avoid application memory issues. By default those are set to 256KB
     */
    int maxInMemorySize = 10 * 1024 * 1024;

    /**
     * If the connection establishment attempt to the remote peer does not finish within the configured connect timeout (resolution: ms),
     * the connection establishment attempt fails. Default: 30s.
     */
    int connectTimeout = 10000;

    /**
     * default response timeout
     */
    int responseTimeout = 3000;

    /**
     * Default max connections. Fallback to 2 * available number of processors (but with a minimum value of 16)
     */
    int maxConnections = 1000;

    /**
     * The maximum number of extra attempts at acquiring a connection to keep in a pending queue.
     * If -1 is specified, the pending queue does not have upper limit. Default to 2 * max connections.
     */
    int maxPendingCount = 3000;

    public WebClientUtil() {
        this.webClient = builder().build();
    }

    public WebClientUtil(int maxInMemorySize, int connectTimeout, int responseTimeout, int maxConnections, int maxPendingCount) {
        if (maxInMemorySize <= 0) {
            maxInMemorySize = 10 * 1024 * 1024;
        }
        this.maxInMemorySize = maxInMemorySize;
        this.connectTimeout = connectTimeout;
        this.responseTimeout = responseTimeout;
        this.maxConnections = maxConnections;
        this.maxPendingCount = maxPendingCount;
        this.webClient = builder().build();
    }

    private WebClient.Builder builder() {
        ConnectionProvider provider = ConnectionProvider.builder("custom")
                .maxConnections(maxConnections)
                .maxIdleTime(Duration.ofSeconds(20))
                .maxLifeTime(Duration.ofSeconds(60))
                .pendingAcquireTimeout(Duration.ofSeconds(600))
                .evictInBackground(Duration.ofSeconds(120))
                .pendingAcquireMaxCount(maxPendingCount)
                .build();

        HttpClient httpClient = HttpClient.create(provider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                .responseTimeout(Duration.ofMillis(responseTimeout))
                .compress(true);

        DefaultUriBuilderFactory ubf = new DefaultUriBuilderFactory();
        ubf.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxInMemorySize))
                .uriBuilderFactory(ubf)
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }

    /**
     * get request
     * @param uri
     * @param headers
     * @param mediaType
     * @return
     */
    public Mono<ResponseEntity<String>> get(String uri, Map<String, String> headers, MediaType mediaType) {
        return invoke(HttpMethod.GET, uri, headers, "", mediaType);
    }

    /**
     * post request
     * @param uri
     * @param headers
     * @param mediaType
     * @param bodyValue
     * @return
     */
    public Mono<ResponseEntity<String>> post(String uri, Map<String, String> headers, MediaType mediaType, Object bodyValue) {
        return invoke(HttpMethod.POST, uri, headers, bodyValue, mediaType);
    }

    public Mono<ResponseEntity<String>> put(String uri, Map<String, String> headers, MediaType mediaType, Object bodyValue) {
        return invoke(HttpMethod.PUT, uri, headers, bodyValue, mediaType);
    }

    /**
     * CompletableFuture
     *
     * @param method
     * @param uri
     * @param headers
     * @param bodyValue
     * @return
     */
    public CompletableFuture<ResponseEntity<String>> invokeForFuture(HttpMethod method,
                                                                     String uri,
                                                                     Map<String, String> headers,
                                                                     MediaType mediaType,
                                                                     String bodyValue) {
        return invoke(method, uri, headers, bodyValue, mediaType).toFuture();
    }

    public CompletableFuture<ResponseEntity<String>> getForFuture(String uri, Map<String, String> headers, MediaType mediaType) {
        return invokeForFuture(HttpMethod.GET, uri, headers, mediaType, "");
    }

    public CompletableFuture<ResponseEntity<String>> postForFuture(String uri, Map<String, String> headers, String bodyValue, MediaType mediaType) {
        return invokeForFuture(HttpMethod.POST, uri, headers, mediaType, bodyValue);
    }

    public Mono<ResponseEntity<String>> invoke(HttpMethod method,
                                               String uri,
                                               Map<String, String> headers,
                                               Object bodyValue, MediaType mediaType) {

        if (null == method) {
            return Mono.error(new RuntimeException("method_is_empty"));
        }

        if (!supports.contains(method)) {
            return Mono.error(new RuntimeException("invalid_method"));
        }

        if (!StringUtils.hasText(uri)) {
            return Mono.error(new RuntimeException("uri_is_empty"));
        }

        if (null == bodyValue) {
            bodyValue = "";
        }

        return webClient
                .method(method)
                .uri(uri)
                .contentType(mediaType)
                .headers(httpHeaders -> addHeaders(httpHeaders, headers))
                .bodyValue(bodyValue)
                .retrieve()
                .toEntity(String.class)
                .doOnError(WebClientRequestException.class, e -> {
                    if (e.getCause() instanceof ReadTimeoutException) {
                        System.out.println("request timeout");
                    }
                });
    }

    void addHeaders(HttpHeaders httpHeaders, Map<String, String> headers) {
        if (null != headers && headers.size() > 0) {
            httpHeaders.setAll(headers);
        }
    }
}
