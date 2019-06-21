FROM openjdk:8-jdk-alpine
ADD target/pricing-service.jar pricing-service.jar
EXPOSE 8182
ENTRYPOINT ["java", "-jar", "pricing-service.jar"]