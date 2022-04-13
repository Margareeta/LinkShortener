FROM openjdk:17-alpine
ARG LINK=target/*.jar
COPY ${SHORTLINK_JAR} Shortlink.jar
ENTRYPOINT ["java", "-jar", "/Shortlink.jar"]