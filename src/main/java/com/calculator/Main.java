package com.calculator;

import java.util.Scanner;

/**
 * Main entry point for the Java Calculator application.
 * Provides an interactive console interface for performing calculations.
 */
public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("    Java Calculator App      ");
        System.out.println("=============================");
        System.out.println("Operations: +, -, *, /");
        System.out.println("Type 'exit' to quit.");
        System.out.println();

        while (true) {
            System.out.print("Enter expression (e.g. 10 + 5): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                String[] parts = input.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Invalid input. Use format: <number> <operator> <number>");
                    continue;
                }

                double a = Double.parseDouble(parts[0]);
                String operator = parts[1];
                double b = Double.parseDouble(parts[2]);
                double result;

                switch (operator) {
                    case "+":
                        result = calculator.add(a, b);
                        break;
                    case "-":
                        result = calculator.subtract(a, b);
                        break;
                    case "*":
                        result = calculator.multiply(a, b);
                        break;
                    case "/":
                        result = calculator.divide(a, b);
                        break;
                    default:
                        System.out.println("Unknown operator: " + operator + ". Use +, -, *, /");
                        continue;
                }

                System.out.printf("Result: %.2f%n%n", result);

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Please enter valid numbers.");
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
