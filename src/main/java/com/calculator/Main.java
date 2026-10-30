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
        System.out.println("Type 'exit' at any prompt to quit.");
        System.out.println();

        while (true) {
            // --- First number ---
            System.out.print("Enter first number (a): ");
            String inputA = scanner.nextLine().trim();
            if (inputA.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            double a;
            try {
                a = Double.parseDouble(inputA);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number. Please enter a valid number.");
                System.out.println();
                continue;
            }

            // --- Operator ---
            System.out.print("Enter operation (+, -, *, /): ");
            String operator = scanner.nextLine().trim();
            if (operator.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (!operator.equals("+") && !operator.equals("-")
                    && !operator.equals("*") && !operator.equals("/")) {
                System.out.println("Error: Unknown operator '" + operator + "'. Use +, -, *, /");
                System.out.println();
                continue;
            }

            // --- Second number ---
            System.out.print("Enter second number (b): ");
            String inputB = scanner.nextLine().trim();
            if (inputB.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            double b;
            try {
                b = Double.parseDouble(inputB);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number. Please enter a valid number.");
                System.out.println();
                continue;
            }

            // --- Compute ---
            try {
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
                        // Already validated above; should never reach here.
                        continue;
                }
                System.out.printf("Result: %.2f%n%n", result);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            }
        }

        scanner.close();
    }
}
