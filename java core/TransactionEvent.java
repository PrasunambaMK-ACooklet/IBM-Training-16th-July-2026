package com.bank.events;

import com.bank.model.TransactionType;
import java.time.LocalDateTime;

/**
 * TOPIC: Event Management Usecase
 *
 * An immutable data holder describing "something happened" - in this case a
 * completed banking transaction. This object is what gets passed to every
 * registered TransactionListener when an event fires.
 */
public final class TransactionEvent {

    private final String accountNumber;
    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime timestamp;

    public TransactionEvent(String accountNumber, TransactionType type,
                             double amount, double balanceAfter) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + " of " + amount +
                " on account " + accountNumber + " -> new balance " + balanceAfter;
    }
}
