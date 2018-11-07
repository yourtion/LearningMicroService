FROM openjdk:11-jre-slim
MAINTAINER Yourtion yourtion@gmail.com

EXPOSE 8081
VOLUME /tmp

ENV DUBBO_REGISTRY "zookeeper://zookeeper:2181"

ARG JAR_FILE="target/course-edge-service-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
