FROM ubuntu:latest AS build
RUN  apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-slim
EXPOSE 80
COPY target/mock-server.jar mock-server.jar

ENTRYPOINT ["java", "-jar", "./mock-server.jar"]

