# Java Calculator

A simple Java calculator application built with Maven, developed as part of the SPE (Software Production Engineering) Mini-Project.

## Features

- Basic arithmetic: **addition**, **subtraction**, **multiplication**, **division**
- Input validation and clear error messages
- Division-by-zero protection
- Interactive console interface

## Requirements

- Java 17+
- Maven 3.6+

## Build

```bash
mvn clean package
```

## Run Tests

```bash
mvn test
```

## Run the Application

```bash
java -jar target/java-calculator-1.0-SNAPSHOT.jar
```

Or directly:

```bash
mvn exec:java -Dexec.mainClass="com.calculator.Main"
```

## Usage

```
=============================
    Java Calculator App
=============================
Operations: +, -, *, /
Type 'exit' to quit.

Enter expression (e.g. 10 + 5): 10 + 5
Result: 15.00

Enter expression (e.g. 10 + 5): 9 / 3
Result: 3.00

Enter expression (e.g. 10 + 5): exit
Goodbye!
```

## Project Structure

```
java-calculator/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/calculator/
    │   ├── Calculator.java    # Core arithmetic logic
    │   └── Main.java          # Console entry point
    └── test/java/com/calculator/
        └── CalculatorTest.java  # JUnit 5 unit tests
```
