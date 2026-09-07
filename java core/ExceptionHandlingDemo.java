package com.bank.exceptions;

import com.bank.core.Account;
import com.bank.core.SavingsAccount;
import com.bank.model.Customer;

/**
 * TOPIC: Exception Handling and Assertions
 *
 * Demonstrates try/catch/finally, multi-catch, custom checked/unchecked
 * exceptions, and Java's assert statement.
 *
 * NOTE on assertions: they are disabled by default at runtime. To see the
 * AssertionError below, run with:  java -ea com.bank.exceptions.ExceptionHandlingDemo
 */
public class ExceptionHandlingDemo {

    public static void main(String[] args) {
        Customer customer = new Customer("CUST-09", "Kabir Malhotra", "kabir@example.com");
        Account account = new SavingsAccount("SB-9001", customer, 1500.0);

        // ---- try / catch / finally ----
        try {
            account.withdraw(10000.0); // will breach both balance and min-balance rule
            System.out.println("Withdrawal succeeded");
        } catch (InsufficientFundsException e) {
            System.out.println("Caught InsufficientFundsException: " + e.getMessage() +
                    " (short by " + e.getShortfall() + ")");
        } finally {
            System.out.println("finally block always runs - e.g. to close a DB connection/log the attempt");
        }

        // ---- multi-catch for an unchecked exception ----
        try {
            account.deposit(-500.0); // triggers InvalidAmountException (unchecked)
        } catch (InvalidAmountException | IllegalStateException e) {
            System.out.println("Caught business-rule violation: " + e.getMessage());
        }

        // ---- assertions: internal self-checks, NOT for validating user input ----
        double balanceBefore = account.getBalance();
        account.deposit(100.0);
        double balanceAfter = account.getBalance();
        assert balanceAfter == balanceBefore + 100.0 :
                "Invariant broken: deposit did not increase balance by the exact amount";
        System.out.println("Assertion passed (or assertions are disabled). Balance: " + balanceAfter);

        System.out.println("Program continues normally after handled exceptions.");
    }
}
