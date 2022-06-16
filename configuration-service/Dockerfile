# 以jdk11为基础镜像
FROM openjdk:11
# 描述
LABEL description="Java 11"

# 暴露接口
EXPOSE 8886
# 将主机中的jar包添加到镜像中
ADD ./target/configuration-service-0.0.1-SNAPSHOT.jar configuration-service.jar
# 运行jar包
ENTRYPOINT ["java", "-jar","configuration-service.jar"]