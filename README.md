# Java Calculator

A simple Java calculator application built with Maven, developed as part of the **CSE 816 — Software Production Engineering** Mid-Term Project. The project demonstrates a complete DevOps pipeline with Git, Maven, JUnit 5, Docker, Docker Compose, Jenkins CI/CD, and Nginx.

## Features

- Basic arithmetic: **addition**, **subtraction**, **multiplication**, **division**
- Input validation and clear error messages
- Division-by-zero protection
- Overflow/NaN guard on all operations
- Interactive console interface

## Tech Stack

| Component        | Technology                          |
| ---------------- | ----------------------------------- |
| Language         | Java 21                             |
| Build Tool       | Apache Maven 3.9                    |
| Testing          | JUnit 5.10.0 + Maven Surefire 3.1.2 |
| CI/CD            | Jenkins (Declarative Pipeline)      |
| Containerisation | Docker (multi-stage build)          |
| Orchestration    | Docker Compose                      |
| Reverse Proxy    | Nginx (Alpine)                      |
| Version Control  | Git / GitHub                        |

## Requirements

- Java 17+
- Maven 3.6+
- Docker & Docker Compose (for containerised deployment)

## Build

```bash
mvn clean package
```

## Run Tests

```bash
mvn test
```

All **19 JUnit 5 tests** pass (4 addition + 3 subtraction + 4 multiplication + 5 division + 3 overflow/NaN).

## Run the Application

```bash
java -jar target/java-calculator-1.0-SNAPSHOT.jar
```

## Usage

The application prompts for each input separately — first number `a`, the operator, then second number `b`.

```
=============================
    Java Calculator App
=============================
Operations: +, -, *, /
Type 'exit' at any prompt to quit.

Enter first number (a): 10
Enter operation (+, -, *, /): +
Enter second number (b): 5
Result: 15.00

Enter first number (a): 9
Enter operation (+, -, *, /): /
Enter second number (b): 3
Result: 3.00

Enter first number (a): exit
Goodbye!
```

## Docker

### Build the image

```bash
docker build -t greatwarrior44/java-calculator:latest .
```

### Run the container

```bash
docker run -it greatwarrior44/java-calculator:latest
```

The Dockerfile uses a **multi-stage build** — a Maven builder stage compiles the JAR, and a minimal `eclipse-temurin:21-jre-alpine` runtime stage runs it under a non-root user (`appuser`).

## Docker Compose

```bash
docker compose up -d
```

This brings up two services:

| Service      | Image                                   | Port      | Purpose          |
| ------------ | --------------------------------------- | --------- | ---------------- |
| `calculator` | `greatwarrior44/java-calculator:latest` | —         | Java console app |
| `nginx`      | `nginx:alpine`                          | `8088:80` | Status dashboard |

Access the Nginx status dashboard at: **http://localhost:8088**

## CI/CD Pipeline (Jenkins)

The project includes a `Jenkinsfile` defining a declarative pipeline with **6 stages**:

```
Checkout → Build → Test → Docker Build → Docker Push → Deploy
```

- **SCM Polling** (`H/2 * * * *`) triggers builds automatically on new commits
- **JUnit test results** are published to Jenkins
- **Docker images** are built, tagged (`:BUILD_NUMBER` + `:latest`), and pushed to Docker Hub
- **Docker Compose** deploys the application
- **Email notifications** are sent on success/failure via Gmail SMTP

### Jenkins Credentials Required

| Credential ID        | Type                   | Purpose             |
| -------------------- | ---------------------- | ------------------- |
| `docker-credentials` | Username with password | Docker Hub auth     |
| Gmail SMTP           | Username with password | Email notifications |

## Project Structure

```
java-calculator/
├── pom.xml                     # Maven project configuration
├── Dockerfile                  # Multi-stage Docker build
├── docker-compose.yml          # Compose orchestration
├── Jenkinsfile                 # Declarative CI/CD pipeline
├── README.md
├── .gitignore
├── .dockerignore
├── nginx/
│   ├── default.conf            # Nginx server configuration
│   └── index.html              # Status dashboard page
└── src/
    ├── main/java/com/calculator/
    │   ├── Calculator.java     # Core arithmetic logic
    │   └── Main.java           # Console entry point
    └── test/java/com/calculator/
        └── CalculatorTest.java # JUnit 5 unit tests (19 tests)
```

## Author

**Chaitya Shah** — IMT2023055

## Links

- **GitHub**: [https://github.com/CShah44/java-calculator-spe](https://github.com/CShah44/java-calculator-spe)
- **Docker Hub**: [https://hub.docker.com/r/greatwarrior44/java-calculator](https://hub.docker.com/r/greatwarrior44/java-calculator)
