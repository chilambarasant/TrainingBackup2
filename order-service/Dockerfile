FROM openjdk:8-jdk-alpine
ADD target/order-service.jar order-service.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "order-service.jar"]