# Use OpenJDK 11 as base
FROM openjdk:11

# Set working directory inside container
WORKDIR /app

# Copy the Spring Boot jar into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Command to run the jar
ENTRYPOINT ["java","-jar","app.jar"]
