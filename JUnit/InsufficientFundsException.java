/**
 * Thrown when a withdrawal/transfer would take an account below its
 * allowed balance. Used throughout the test-topic examples in this folder
 * as the "expected exception" that unit tests assert against.
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
