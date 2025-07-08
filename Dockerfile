# Use OpenJDK 23 as the base image
FROM eclipse-temurin:23-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/campus_navigation_system_and_lecturer_availability_api-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
