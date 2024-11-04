# Dockerfile for auth-service
FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/auth-player-0.0.1-SNAPSHOT.jar auth-player.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "auth-player.jar"]
