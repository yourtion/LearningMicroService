FROM openjdk:11-jre-slim
MAINTAINER Yourtion yourtion@gmail.com

EXPOSE 8080
VOLUME /tmp

ENV ROUTE_COURSE_HOST course-edge-service
ENV ROUTE_COURSE_PORT 8081
ENV ROUTE_USER_HOST user-edge-service
ENV ROUTE_USER_PORT 8082

ARG JAR_FILE="target/api-gateway-zuul-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
