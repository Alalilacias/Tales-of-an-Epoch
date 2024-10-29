FROM openjdk:21-jdk
LABEL authors="Allalilacias"

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src src/

RUN ./gradlew clean build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/your-app-name-0.0.1-SNAPSHOT.jar"]
