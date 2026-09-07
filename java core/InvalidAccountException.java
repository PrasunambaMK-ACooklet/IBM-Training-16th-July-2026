package com.bank.exceptions;

/**
 * TOPIC: Exception Handling and Assertions
 *
 * Thrown when an operation is attempted on an account that does not exist
 * or is closed/frozen. Another example of a custom checked exception.
 */
public class InvalidAccountException extends Exception {

    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
