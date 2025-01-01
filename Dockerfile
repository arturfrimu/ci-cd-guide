# Use a Maven image to build the application
FROM maven:3.9.4-eclipse-temurin-17 as builder
WORKDIR /app

# Copy the project files to the container
COPY pom.xml ./
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a lightweight JRE base image for the runtime
FROM eclipse-temurin:17-jre
WORKDIR /app

# Expose the application port
EXPOSE 8080

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/first-deploy-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
