# Use OpenJDK 11 as base
FROM openjdk:11-jre-slim

# Set working directory inside container
WORKDIR /app

# Build argument for the jar file
ARG JAR_FILE=target/*.jar

# Copy the jar into the container
COPY ${JAR_FILE} app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
