# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app

# Download all required dependencies (caching optimization)
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src /app/src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/cybriabank-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
