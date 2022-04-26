FROM openjdk:17-alpine
ARG AUTHENTICATION_JAR=target/*.jar
COPY ${AUTHENTICATION_JAR} authenticationservice.jar
ENTRYPOINT ["java", "-jar", "/authenticationservice.jar"]