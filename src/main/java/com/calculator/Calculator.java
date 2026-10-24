package com.calculator;

/**
 * Core Calculator class providing basic arithmetic operations.
 * All operations guard against double overflow (Infinity) and NaN results.
 */
public class Calculator {

    /**
     * Adds two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the sum of a and b
     * @throws ArithmeticException if the result overflows to Infinity or is NaN
     */
    public double add(double a, double b) {
        double result = a + b;
        checkResult(result, "Addition");
        return result;
    }

    /**
     * Subtracts b from a.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the result of a minus b
     * @throws ArithmeticException if the result overflows to Infinity or is NaN
     */
    public double subtract(double a, double b) {
        double result = a - b;
        checkResult(result, "Subtraction");
        return result;
    }

    /**
     * Multiplies two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the product of a and b
     * @throws ArithmeticException if the result overflows to Infinity or is NaN
     */
    public double multiply(double a, double b) {
        double result = a * b;
        checkResult(result, "Multiplication");
        return result;
    }

    /**
     * Divides a by b.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the result of a divided by b
     * @throws ArithmeticException if b is zero, or if the result overflows
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        double result = a / b;
        checkResult(result, "Division");
        return result;
    }

    /**
     * Validates that a computed result is a finite, usable number.
     *
     * @param result    the value to check
     * @param operation the name of the operation (used in the error message)
     * @throws ArithmeticException if the result is Infinite or NaN
     */
    private void checkResult(double result, String operation) {
        if (Double.isInfinite(result)) {
            throw new ArithmeticException(
                    operation + " result overflowed to Infinity. Inputs are too large.");
        }
        if (Double.isNaN(result)) {
            throw new ArithmeticException(
                    operation + " produced an undefined result (NaN).");
        }
    }
}
