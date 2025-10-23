# Use a lightweight JDK 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the backend fat JAR (shaded) into the container
COPY target/Order-backend-1.0-SNAPSHOT.jar app.jar

# Expose the backend port
EXPOSE 8080

# Run the backend application
ENTRYPOINT ["java", "-jar", "app.jar"]







