package com.bank.exceptions;

/**
 * TOPIC: Exception Handling and Assertions
 *
 * An UNCHECKED exception (extends RuntimeException). Passing a negative or
 * zero amount to deposit/withdraw is treated as a programming error by the
 * caller, so we don't force every method up the call chain to declare it.
 */
public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(String message) {
        super(message);
    }
}
