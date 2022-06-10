# 以jdk11为基础镜像
FROM openjdk:11
# 描述
LABEL description="Java 11"

# 暴露接口
EXPOSE 8886
# 将主机中的jar包添加到镜像中
ADD ./target/SeparateSchemaMultitenancy-1.0.jar SeparateSchemaMultitenancy.jar
# 运行jar包
ENTRYPOINT ["java", "-jar","SeparateSchemaMultitenancy.jar"]