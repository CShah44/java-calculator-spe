# ──────────────────────────────────────────────────────────────
# Stage 1 – Build
#   Uses a full JDK + Maven image to compile and package the JAR.
#   Dependencies are resolved in a separate layer so they are
#   cached by Docker and only re-downloaded when pom.xml changes.
# ──────────────────────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml first and resolve dependencies (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests -q

# ──────────────────────────────────────────────────────────────
# Stage 2 – Runtime
#   Uses a minimal JRE-only image; only the packaged JAR is
#   copied over. A non-root user is created for security.
# ──────────────────────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

# Create a non-root user and group
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy only the executable JAR from the builder stage
COPY --from=builder /app/target/java-calculator-1.0-SNAPSHOT.jar app.jar

# Switch to non-root user
USER appuser

# Expose a port (reserved for future HTTP/REST extension)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
