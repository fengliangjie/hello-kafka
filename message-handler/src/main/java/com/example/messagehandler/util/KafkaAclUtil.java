package com.example.messagehandler.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateAclsResult;
import org.apache.kafka.common.acl.AccessControlEntry;
import org.apache.kafka.common.acl.AclBinding;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.acl.AclPermissionType;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePattern;
import org.apache.kafka.common.resource.ResourceType;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author: liangjie.feng
 * @date: 2022/6/7 6:16 PM
 */
@Component
public class KafkaAclUtil {

    private final AdminClient client;

    KafkaAclUtil() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put("security.protocol","SSL");
        /**
         * 用来开通acl权限的 jks 应该是固定的，也就是管理员的账号
         */
        props.put("ssl.truststore.location","/Users/hakurei/Projects/kafka-ssl/message-handler/src/main/resources/kafka.server.truststore.jks");
        props.put("ssl.truststore.password", "123456");
        props.put("ssl.keystore.location", "/Users/hakurei/Projects/kafka-ssl/message-handler/src/main/resources/kafka.server.keystore.jks");
        props.put("ssl.keystore.password", "123456");
        props.put("ssl.key.password", "123456");
        client = AdminClient.create(props);
    }

    /**
     * 若要用 api 为某个租户开通acl权限，则用来开通权限的租户必须已经对kafka中至少一个topic拥有权限，否则会报 not authorized 错误
     *
     * @param topic
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void createAcl(String topic, String principal, String group) throws ExecutionException, InterruptedException {
        AclBinding producer = new AclBinding(new ResourcePattern(ResourceType.TOPIC, topic, PatternType.LITERAL), new AccessControlEntry(principal, "*", AclOperation.ALL, AclPermissionType.ALLOW));
        AclBinding consumer = new AclBinding(new ResourcePattern(ResourceType.GROUP, group, PatternType.LITERAL), new AccessControlEntry(principal, "*", AclOperation.ALL, AclPermissionType.ALLOW));
        Set<AclBinding> set = new HashSet<>();
        set.add(producer);
        set.add(consumer);
        CreateAclsResult result = client.createAcls(set);
        result.all().get();
    }
}
