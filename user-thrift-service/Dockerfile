FROM openjdk:11-jre-slim
MAINTAINER Yourtion yourtion@gmail.com

EXPOSE 7911
VOLUME /tmp

ENV MYSQL_HOST mysql
ENV MYSQL_PORT 3306
ENV MYSQL_DB db_user
ENV MYSQL_USER micro
ENV MYSQL_PASSWORD aA123456

ARG JAR_FILE="target/user-thrift-service-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
