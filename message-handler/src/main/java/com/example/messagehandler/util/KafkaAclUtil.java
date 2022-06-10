package com.example.messagehandler.util;

import lombok.Data;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateAclsResult;
import org.apache.kafka.common.acl.AccessControlEntry;
import org.apache.kafka.common.acl.AclBinding;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.acl.AclPermissionType;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePattern;
import org.apache.kafka.common.resource.ResourceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: liangjie.feng
 * @date: 2022/6/7 6:16 PM
 */
@Data
public class KafkaAclUtil {

    private final AdminClient client;

    public KafkaAclUtil(String servers, String securityProtocol, String trustStoreLocation, String trustStorePassword, String keyStoreLocation, String keyStorePassword, String keyPassword) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        /**
         * 用来开通acl权限的 jks 应该是固定的，也就是管理员的账号
         */
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keyStoreLocation);
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, keyStorePassword);
        props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, keyPassword);
        client = AdminClient.create(props);
    }

    /**
     * 若要用 api 为某个租户开通acl权限，则用来开通权限的租户必须已经对kafka中至少一个topic拥有权限，否则会报 not authorized 错误
     *
     * @param topic
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void createAcl(String topic, String principal, String group) {
        AclBinding producer = new AclBinding(new ResourcePattern(ResourceType.TOPIC, topic, PatternType.LITERAL), new AccessControlEntry(principal, "*", AclOperation.ALL, AclPermissionType.ALLOW));
        AclBinding consumer = new AclBinding(new ResourcePattern(ResourceType.GROUP, group, PatternType.LITERAL), new AccessControlEntry(principal, "*", AclOperation.ALL, AclPermissionType.ALLOW));
        Set<AclBinding> set = new HashSet<>();
        set.add(producer);
        set.add(consumer);
        CreateAclsResult result = client.createAcls(set);
        try {
            result.all().get(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
