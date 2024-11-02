# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the host to the container
COPY build/libs/auth-player-0.0.1-SNAPSHOT.jar auth-player.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "auth-player.jar"]
