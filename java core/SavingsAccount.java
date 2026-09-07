package com.bank.core;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.model.AccountType;
import com.bank.model.Customer;

/**
 * TOPIC: Inheritance, Polymorphism
 *
 * SavingsAccount extends (inherits from) Account, reusing all of its
 * fields/methods and adding its own behaviour: a minimum balance rule and
 * monthly interest credit. Overriding withdraw() and applyMonthlyMaintenance()
 * is what makes runtime polymorphism possible - a variable of type Account
 * that actually points to a SavingsAccount will run THIS withdraw(), not
 * the parent's.
 */
public class SavingsAccount extends Account {

    private static final double MIN_BALANCE = 1000.0;

    public SavingsAccount(String accountNumber, Customer owner, double openingBalance) {
        super(accountNumber, owner, AccountType.SAVINGS, openingBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientFundsException(
                    "Withdrawal would breach minimum balance of " + MIN_BALANCE,
                    MIN_BALANCE - (getBalance() - amount));
        }
        super.withdraw(amount); // reuse the parent's core logic
    }

    @Override
    public void applyMonthlyMaintenance() {
        double interest = getBalance() * (getType().getAnnualInterestRate() / 100.0 / 12.0);
        deposit(interest);
        System.out.printf("Savings a/c %s credited with interest: %.2f%n", getAccountNumber(), interest);
    }

    @Override
    public String describe() {
        return "SavingsAccount[" + getAccountNumber() + ", owner=" + getOwner().getFullName() + "]";
    }
}
