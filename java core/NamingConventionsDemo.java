package com.bank.cleancode;

import java.util.ArrayList;
import java.util.List;

/**
 * TOPIC: Meaningful Naming Conventions
 *
 * Good names make code self-documenting. This file deliberately shows a
 * "BAD" version next to a "GOOD" version of the same logic so the
 * difference is obvious side by side.
 */
public class NamingConventionsDemo {

    // ---------------- BAD: unclear, abbreviated, inconsistent names ----------------
    static double calc(double a, double r, int n) {
        double x = a; // what is "x"? what is "a", "r", "n"?
        for (int i = 0; i < n; i++) {
            x = x + (x * r / 100);
        }
        return x;
    }

    // ---------------- GOOD: names reveal intent without needing comments ----------------
    static double calculateCompoundedBalance(double openingBalance, double annualInterestRatePercent, int numberOfYears) {
        double balance = openingBalance;
        for (int year = 0; year < numberOfYears; year++) {
            balance = balance + (balance * annualInterestRatePercent / 100);
        }
        return balance;
    }

    public static void main(String[] args) {
        System.out.println("Bad naming, same result: " + calc(10000, 6, 3));
        System.out.println("Good naming, same result: " + calculateCompoundedBalance(10000, 6, 3));

        // Java naming conventions used consistently across this whole project:
        //   Classes/Interfaces : UpperCamelCase        -> SavingsAccount, TransactionListener
        //   Methods/variables  : lowerCamelCase         -> calculateInterest, accountBalance
        //   Constants          : UPPER_SNAKE_CASE       -> MIN_BALANCE, OVERDRAFT_LIMIT
        //   Packages           : all.lowercase.dotted   -> com.bank.core

        final int MAX_LOGIN_ATTEMPTS = 3; // constant naming convention
        List<String> flaggedAccountNumbers = new ArrayList<>(); // boolean/collection names read naturally
        boolean isAccountLocked = false;

        System.out.println("MAX_LOGIN_ATTEMPTS = " + MAX_LOGIN_ATTEMPTS);
        System.out.println("isAccountLocked = " + isAccountLocked);
        System.out.println("flaggedAccountNumbers = " + flaggedAccountNumbers);
    }
}
