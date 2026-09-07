package com.bank.core;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.model.AccountType;
import com.bank.model.Customer;

/**
 * TOPIC: Abstraction, Encapsulation
 *
 * ABSTRACTION: Account is declared abstract - it defines WHAT every account
 * must be able to do (withdraw, describe its type) without saying exactly
 * HOW each concrete account type does it. You can never say
 * "new Account(...)"; you must use a concrete subclass.
 *
 * ENCAPSULATION: every field is private. The balance can only ever change
 * through the controlled deposit()/withdraw() methods, which enforce rules
 * (no negative amounts, no overdrawing) - the caller cannot corrupt the
 * balance directly.
 */
public abstract class Account {

    private static int accountsCreatedCount = 0; // shared across all accounts

    private final String accountNumber;
    private final Customer owner;
    private final AccountType type;
    private double balance;

    protected Account(String accountNumber, Customer owner, AccountType type, double openingBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.type = type;
        this.balance = openingBalance;
        accountsCreatedCount++;
    }

    public static int getAccountsCreatedCount() {
        return accountsCreatedCount;
    }

    public final String getAccountNumber() {
        return accountNumber;
    }

    public final Customer getOwner() {
        return owner;
    }

    public final AccountType getType() {
        return type;
    }

    public final double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive, got: " + amount);
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive, got: " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds in account " + accountNumber, amount - balance);
        }
        balance -= amount;
    }

    /**
     * Lets a subclass adjust the balance directly, bypassing the public
     * deposit()/withdraw() validation. Declared "protected" (not private)
     * specifically so subclasses such as CurrentAccount can implement their
     * own rules (e.g. allowing a negative/overdrawn balance).
     */
    protected final void adjustBalance(double delta) {
        balance += delta;
    }

    /**
     * Abstract method - every concrete account type MUST provide its own
     * monthly maintenance/interest rule. This is where POLYMORPHISM kicks
     * in: calling this method on an Account reference runs the version
     * that matches the object's real (runtime) type.
     */
    public abstract void applyMonthlyMaintenance();

    /** Concrete accounts describe themselves in their own words. */
    public abstract String describe();

    @Override
    public String toString() {
        return describe() + " | balance=" + balance;
    }
}
