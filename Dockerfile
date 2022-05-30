FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} secret-keeper-api.jar
ARG APPLICATION_PROPERTIES=src/main/resources/application-dev.properties
COPY ${APPLICATION_PROPERTIES} application.properties
ENTRYPOINT ["java","-jar","/secret-keeper-api.jar --spring.config.location=/application.properties"]
