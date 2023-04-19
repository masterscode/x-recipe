FROM eclipse-temurin:17-alpine
ADD target/mock-server.jar mock-server.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "decapay.jar"]