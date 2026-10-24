package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Calculator class using JUnit 5.
 */
@DisplayName("Calculator Unit Tests")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ── Addition ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Add two positive numbers")
    void testAddPositiveNumbers() {
        assertEquals(8.0, calculator.add(5, 3), "5 + 3 should equal 8");
    }

    @Test
    @DisplayName("Add a positive and a negative number")
    void testAddPositiveAndNegative() {
        assertEquals(2.0, calculator.add(5, -3), "5 + (-3) should equal 2");
    }

    @Test
    @DisplayName("Add two negative numbers")
    void testAddNegativeNumbers() {
        assertEquals(-8.0, calculator.add(-5, -3), "-5 + (-3) should equal -8");
    }

    @Test
    @DisplayName("Add with zero")
    void testAddWithZero() {
        assertEquals(5.0, calculator.add(5, 0), "5 + 0 should equal 5");
    }

    // ── Subtraction ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("Subtract two positive numbers")
    void testSubtractPositiveNumbers() {
        assertEquals(2.0, calculator.subtract(5, 3), "5 - 3 should equal 2");
    }

    @Test
    @DisplayName("Subtract resulting in a negative number")
    void testSubtractResultNegative() {
        assertEquals(-2.0, calculator.subtract(3, 5), "3 - 5 should equal -2");
    }

    @Test
    @DisplayName("Subtract from zero")
    void testSubtractFromZero() {
        assertEquals(-5.0, calculator.subtract(0, 5), "0 - 5 should equal -5");
    }

    // ── Multiplication ────────────────────────────────────────────────────────

    @Test
    @DisplayName("Multiply two positive numbers")
    void testMultiplyPositiveNumbers() {
        assertEquals(15.0, calculator.multiply(5, 3), "5 * 3 should equal 15");
    }

    @Test
    @DisplayName("Multiply by zero")
    void testMultiplyByZero() {
        assertEquals(0.0, calculator.multiply(5, 0), "5 * 0 should equal 0");
    }

    @Test
    @DisplayName("Multiply two negative numbers")
    void testMultiplyTwoNegatives() {
        assertEquals(15.0, calculator.multiply(-5, -3), "-5 * -3 should equal 15");
    }

    @Test
    @DisplayName("Multiply a positive and a negative number")
    void testMultiplyPositiveAndNegative() {
        assertEquals(-15.0, calculator.multiply(5, -3), "5 * -3 should equal -15");
    }

    // ── Division ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Divide two positive numbers")
    void testDividePositiveNumbers() {
        assertEquals(4.0, calculator.divide(12, 3), "12 / 3 should equal 4");
    }

    @Test
    @DisplayName("Divide resulting in a decimal")
    void testDivideDecimalResult() {
        assertEquals(2.5, calculator.divide(5, 2), "5 / 2 should equal 2.5");
    }

    @Test
    @DisplayName("Divide by zero throws ArithmeticException")
    void testDivideByZeroThrowsException() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0),
                "Dividing by zero should throw ArithmeticException"
        );
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

    @Test
    @DisplayName("Divide zero by a number")
    void testDivideZeroByNumber() {
        assertEquals(0.0, calculator.divide(0, 5), "0 / 5 should equal 0");
    }

    @Test
    @DisplayName("Divide a negative by a positive")
    void testDivideNegativeByPositive() {
        assertEquals(-3.0, calculator.divide(-9, 3), "-9 / 3 should equal -3");
    }

    // ── Overflow / NaN guards ─────────────────────────────────────────────────

    @Test
    @DisplayName("Multiply very large numbers throws ArithmeticException on overflow")
    void testMultiplyOverflow() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.multiply(Double.MAX_VALUE, 2),
                "Multiplying Double.MAX_VALUE by 2 should overflow to Infinity"
        );
        assertTrue(exception.getMessage().contains("overflowed to Infinity"));
    }

    @Test
    @DisplayName("Add very large numbers throws ArithmeticException on overflow")
    void testAddOverflow() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.add(Double.MAX_VALUE, Double.MAX_VALUE),
                "Adding Double.MAX_VALUE to itself should overflow"
        );
    }

    @Test
    @DisplayName("Subtract very large numbers throws ArithmeticException on overflow")
    void testSubtractOverflow() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.subtract(-Double.MAX_VALUE, Double.MAX_VALUE),
                "Subtracting to -Infinity should throw"
        );
    }
}
