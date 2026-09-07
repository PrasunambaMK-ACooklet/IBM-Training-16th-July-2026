package com.bank.core;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.model.AccountType;
import com.bank.model.Customer;

/**
 * TOPIC: Inheritance, Polymorphism
 *
 * A second subclass of Account with completely different rules (overdraft
 * allowed, flat monthly fee instead of interest). Having two subclasses
 * side by side is what makes the polymorphism in BankDemo.java meaningful -
 * the same loop calling applyMonthlyMaintenance() behaves differently for
 * each object depending on its actual class.
 */
public class CurrentAccount extends Account {

    private static final double OVERDRAFT_LIMIT = 10000.0;
    private static final double MONTHLY_FEE = 50.0;

    public CurrentAccount(String accountNumber, Customer owner, double openingBalance) {
        super(accountNumber, owner, AccountType.CURRENT, openingBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException(
                    "Withdrawal exceeds overdraft limit of " + OVERDRAFT_LIMIT,
                    Math.abs(getBalance() - amount) - OVERDRAFT_LIMIT);
        }
        // Intentionally bypasses Account.withdraw()'s "no negative balance"
        // check (via the protected adjustBalance helper) because overdraft
        // is allowed for a current account.
        adjustBalance(-amount);
    }

    @Override
    public void applyMonthlyMaintenance() {
        deposit(-MONTHLY_FEE);
        System.out.printf("Current a/c %s charged monthly fee: %.2f%n", getAccountNumber(), MONTHLY_FEE);
    }

    @Override
    public String describe() {
        return "CurrentAccount[" + getAccountNumber() + ", owner=" + getOwner().getFullName() + "]";
    }
}
