FROM openjdk:11-jre-slim
MAINTAINER Yourtion yourtion@gmail.com

EXPOSE 8080
VOLUME /tmp

ARG JAR_FILE="target/api-gateway-zuul-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
