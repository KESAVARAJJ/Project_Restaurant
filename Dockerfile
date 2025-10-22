# ============================
# Stage 1: Build the Application
# ============================
# Use a valid Maven + Java 21 base image
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven descriptor (pom.xml) and pre-download dependencies for caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy all source code into the container
COPY src ./src

# Package the application (skip tests to speed up build)
RUN mvn clean package -DskipTests

# ============================
# Stage 2: Run the Application
# ============================
# Use a lightweight Java 21 runtime
FROM eclipse-temurin:21-jre

# Set working directory for runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port used in your Spring Boot app (adjust if yours is 9090)
EXPOSE 9090

# Optional: set active Spring profile (can also be overridden in Render dashboard)
ENV SPRING_PROFILES_ACTIVE=prod

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
