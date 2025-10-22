# ==============================
# Stage 1: Build the Spring Boot app
# ==============================
FROM eclipse-temurin:17-jdk AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and build scripts
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Copy the application source code
COPY src src

# Make gradlew executable
RUN chmod +x gradlew

# Build the application without running tests (faster)
RUN ./gradlew clean bootJar -x test

# ==============================
# Stage 2: Run the built JAR
# ==============================
FROM eclipse-temurin:17-jre

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR file from the previous stage
# Use a wildcard (*) to automatically pick up your JAR regardless of version number
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port (Render will override this with its own PORT)
EXPOSE 8081

# Set the entrypoint for running the app
ENTRYPOINT ["java", "-jar", "app.jar"]
