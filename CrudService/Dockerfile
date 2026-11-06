# Backend image: docker-skill-exam-2400031520:latest (built by ../docker-compose.yml)

# Stage 1: Build Spring Boot application
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src ./src

# Give permission to Maven wrapper
RUN chmod +x mvnw

# Build application (tests need a running PostgreSQL, so they are skipped here)
RUN ./mvnw clean package -DskipTests


# Stage 2: Run Spring Boot application
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy generated JAR file
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
