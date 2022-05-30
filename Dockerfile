FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} secret-keeper-api.jar
ENTRYPOINT ["java","-jar","/secret-keeper-api.jar"]
