package com.siemens.messagehandler.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: liangjie.feng
 * @date: 2022/6/7 6:16 PM
 */
@Data
@Slf4j
public class KafkaAclUtil {

    private final AdminClient client;

    public KafkaAclUtil(String servers, String securityProtocol, String trustStoreLocation, String trustStorePassword, String keyStoreLocation, String keyStorePassword, String keyPassword) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keyStoreLocation);
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, keyStorePassword);
        props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, keyPassword);
        client = AdminClient.create(props);
    }

    /**
     * create acl
     *
     * @param topic
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
            log.info("Activate permissions，topic: {}, user: {}, consumer-group: {}", topic, principal, group);
        } catch (Exception e) {
            log.error("Activate permissions failed");
            e.printStackTrace();
        }
    }
}
