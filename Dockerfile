FROM openjdk:8-jdk-alpine
MAINTAINER waracle.com
ARG JAR_FILE=target/cake-manager-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]