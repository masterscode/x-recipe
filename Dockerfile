FROM ubuntu:latest AS build
RUN  apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./mvnw clean install

FROM openjdk:17-jdk-slim
EXPOSE 80
COPY --from-build /build/libs/mock-server.jar mock-server.jar

ENTRYPOINT ["java", "-jar", "./mock-server.jar"]

