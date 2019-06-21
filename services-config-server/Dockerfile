FROM openjdk:8-jdk-alpine
ADD target/services-config.jar services-config.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "services-config.jar"]