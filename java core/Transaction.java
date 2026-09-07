package com.bank.objectmethods;

import java.util.Objects;

/**
 * TOPIC: Object class methods
 *
 * Every Java class implicitly extends Object and inherits equals(),
 * hashCode(), and toString(). Overriding them correctly is essential for
 * a value-like class such as Transaction, especially if it will ever be
 * placed in a HashSet/HashMap or compared for business equality.
 */
public class Transaction {

    private final String transactionId;
    private final String accountNumber;
    private final double amount;

    public Transaction(String transactionId, String accountNumber, double amount) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Two transactions are considered "equal" if they share the same id -
     * that is our chosen definition of business equality for this class.
     * The contract: equals() must be reflexive, symmetric, transitive and
     * consistent with hashCode().
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;              // reflexive shortcut
        if (other == null || getClass() != other.getClass()) return false;
        Transaction that = (Transaction) other;
        return Objects.equals(transactionId, that.transactionId);
    }

    /**
     * MUST be overridden whenever equals() is overridden: equal objects
     * must produce the same hash code, or HashMap/HashSet will misbehave.
     */
    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    /**
     * toString() is what gets printed by System.out.println(transaction)
     * and shown by debuggers - always override it for meaningful output
     * instead of the default "ClassName@hexHash".
     */
    @Override
    public String toString() {
        return "Transaction{id='" + transactionId + "', account='" + accountNumber +
                "', amount=" + amount + '}';
    }
}
