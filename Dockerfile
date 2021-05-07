FROM openjdk:8u181-jre-stretch

RUN mkdir /app
WORKDIR /app

ARG JAR_FILE=target/cake-manager-1.0.0.jar
COPY ${JAR_FILE} app.jar

ENV JAVA_OPTS="-Xmx2048m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]