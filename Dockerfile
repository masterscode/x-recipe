FROM ubuntu:latest AS build

MAINTAINER masterscode

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

RUN mvn clean install -DskipTests
COPY target/mock-server.jar mock-server.jar
ENTRYPOINT ["java","-jar","/mock-server.jar"]
