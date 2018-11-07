FROM openjdk:11-jre-slim
MAINTAINER Yourtion yourtion@gmail.com

EXPOSE 8082
VOLUME /tmp

ENV THRIFT_USER_HOST "user-thrift-service"
ENV THRIFT_USER_PORT 7911
ENV THRIFT_MESSAGE_HOST "message-thrift-service"
ENV THRIFT_MESSAGE_PORT 9090
ENV REDIS_HOST redis
ENV REDIS_PORT 6379
ENV REDIS_PASS ""

ARG JAR_FILE="target/user-edge-service-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
