FROM openjdk:8-jdk-alpine
ENV DB_LOGIN=$DB_LOGIN
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} secret-keeper-api.jar
#ENTRYPOINT ["java","-Dspring.active.profiles=draft","-jar","/secret-keeper-api.jar"]
ENTRYPOINT ["java","-jar","/secret-keeper-api.jar"]
