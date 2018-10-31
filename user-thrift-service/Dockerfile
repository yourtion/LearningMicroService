FROM openjdk:11-jre-slim

EXPOSE 7911
VOLUME /tmp

ARG JAR_FILE="target/user-thrift-service-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
