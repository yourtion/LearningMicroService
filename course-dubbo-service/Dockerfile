FROM openjdk:11-jre-slim
MAINTAINER Yourtion yourtion@gmail.com

EXPOSE 20880
VOLUME /tmp

ENV MYSQL_HOST mysql
ENV MYSQL_PORT 3306
ENV MYSQL_DB db_course
ENV MYSQL_USER micro
ENV MYSQL_PASSWORD aA123456

ENV DUBBO_REGISTRY "zookeeper://zookeeper:2181"
ENV THRIFT_USER_HOST "user-thrift-service"
ENV THRIFT_USER_PORT 7911

ARG JAR_FILE="target/course-dubbo-service-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
