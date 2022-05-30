FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} secret-keeper-api.jar
#ARG APPLICATION_PROPERTIES=src/main/resources/application-dev.properties
#COPY ${APPLICATION_PROPERTIES} application.properties
ENTRYPOINT ["java","-Dspring.active.profiles=draft -jar","/secret-keeper-api.jar"]
