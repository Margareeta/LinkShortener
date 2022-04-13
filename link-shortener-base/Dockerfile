FROM openjdk:17-alpine
ARG SHORTLINK_JAR=target/*.jar
COPY ${SHORTLINK_JAR} Shortlink.jar
ENTRYPOINT ["java", "-jar", "/Shortlink.jar"]