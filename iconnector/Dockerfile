# 以jdk8为基础镜像
FROM openjdk:11
# 描述
LABEL description="Java 11"

# 暴露接口
EXPOSE 8887
# 将主机中的jar包添加到镜像中
ADD ./target/iConnector-0.0.1-SNAPSHOT.jar iConnector.jar
# 运行jar包
ENTRYPOINT ["java", "-jar","iConnector.jar"]