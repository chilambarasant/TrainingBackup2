FROM openjdk:8-jdk-alpine
ADD target/services-registry.jar services-registry.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "services-registry.jar"]