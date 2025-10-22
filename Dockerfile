# ============================
# Stage 1: Build the Application
# ============================
# Use a valid Maven + Java 17 base image
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven descriptor (pom.xml) and download dependencies first for caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy all source code into the container
COPY src ./src

# Package the application (skipping tests for faster build)
RUN mvn clean package -DskipTests

# ============================
# Stage 2: Run the Application
# ============================
# Use a lightweight JRE image for runtime
FROM eclipse-temurin:17-jre

# Set working directory for runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (make sure it matches your Spring Boot app port, usually 8080 or 9090)
EXPOSE 9090

# Set environment variable for MySQL (optional; Render can override via dashboard)
ENV SPRING_PROFILES_ACTIVE=prod

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
