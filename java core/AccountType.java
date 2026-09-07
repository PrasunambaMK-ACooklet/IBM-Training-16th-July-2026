package com.bank.model;

/**
 * TOPIC: Enums
 *
 * An enum in Java is a special data type that lets a variable be a set of
 * predefined constants. Here it models the different kinds of accounts the
 * bank offers. Enums can have fields, constructors and methods, just like
 * a normal class.
 */
public enum AccountType {

    SAVINGS(4.0, 1000.0),
    CURRENT(0.0, 0.0),
    FIXED_DEPOSIT(7.5, 5000.0);

    private final double annualInterestRate;
    private final double minimumBalance;

    AccountType(double annualInterestRate, double minimumBalance) {
        this.annualInterestRate = annualInterestRate;
        this.minimumBalance = minimumBalance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }
}
