package com.bank.exceptions;

/**
 * TOPIC: Exception Handling and Assertions
 *
 * A custom CHECKED exception (extends Exception, not RuntimeException).
 * Callers are forced by the compiler to either catch it or declare it with
 * "throws", which is appropriate here because "insufficient funds" is a
 * recoverable, expected business condition rather than a programming bug.
 */
public class InsufficientFundsException extends Exception {

    private final double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}
