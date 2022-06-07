一、进入 http://localhost:8888 管理kafka集群

二、创建kafka集群时报错，执行以下命令

    1.docker exec -it zookeeper bash
    2./bin/zkCli.sh
    3.ls /kafka-manager
    4.create /kafka-manager/mutex ""
    5.create /kafka-manager/mutex/locks ""
    6.create /kafka-manager/mutex/leases ""

三、进入kafka容器  docker exec -it kafka /bin/bash

    1.创建话题  
        kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic test
    2.查看话题列表
        kafka-topics.sh --list --zookeeper zookeeper:2181
    3.acl赋权
        kafka-acls.sh --authorizer-properties zookeeper.connect=zookeeper:2181 --add --allow-principal User:"CN=siemens_002" --operation All --topic iconnector-info.siemens_002 --group "*" --cluster
        (消费者被赋权后需重启服务才能收到消息)
        (topics模式：如果一个消费者同时消费多个topic，那么该消费者必须对所有的topic都有权限，否则所有的topic都不能消费
         topicPattern【topic.*】模式：只要有权限的topic都可以消费【比如有两个topic：topic.1, topic.2，即使消费者只有topic.1的权限，没有topic.2的权限，也可以正常发送和消费消息】)
    4.remove acl
        kafka-acls.sh --authorizer-properties zookeeper.connect=zookeeper:2181 --remove --allow-principal User:"CN=siemens_001" --operation All --topic test --group "*" --cluster
    5.list acls
        kafka-acls.sh --authorizer-properties zookeeper.connect=zookeeper:2181 --list --topic test
四、新打开两个shell窗口，分别作为消息生产者和消费者，分别进入kafka容器中  docker exec -it kafka /bin/bash

    1.生产者执行命令
        kafka-console-producer.sh --topic=test --broker-list kafka:9092
    2.消费者执行命令
        kafka-console-consumer.sh --bootstrap-server kafka:9092 --from-beginning --topic test
    3.在生产者窗口发送消息，在消费者窗口成功接收消息，说明kafka 已经启动成功



kafka安全认证

https://www.cnblogs.com/zhouj850/p/15683605.html

https://www.cnblogs.com/zhouj850/p/15630101.html

https://www.jianshu.com/p/9a5208428c79

https://blog.csdn.net/qq_41858402/article/details/118941503

https://www.cnblogs.com/linlf03/p/15355572.html

https://blog.csdn.net/weixin_39587278/article/details/103965766

zhihu.com/search?type=content&q=kafka%20sasl

https://segmentfault.com/a/1190000022245956