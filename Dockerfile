# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory to the root
WORKDIR /

# Copy the pom.xml and download dependencies
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim

# Copy the jar file from the build stage
COPY --from=build /target/leanring-management-system-0.0.1-SNAPSHOT.jar .

# Run the application
ENTRYPOINT ["java", "-jar", "leanring-management-system-0.0.1-SNAPSHOT.jar"]
