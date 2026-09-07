package com.bank.core;

import com.bank.model.Customer;

/**
 * TOPIC: Polymorphism
 *
 * COMPILE-TIME (overloading): two process() methods with the same name but
 * different parameter lists - the compiler picks the right one based on the
 * arguments you pass.
 *
 * RUNTIME (overriding): the accounts[] array is typed as Account, but each
 * element's applyMonthlyMaintenance()/describe() call runs the SUBCLASS's
 * version, chosen at runtime based on the object's real type.
 */
public class PolymorphismDemo {

    // Overload #1
    static void process(Account account) {
        System.out.println("Processing generic account: " + account.getAccountNumber());
    }

    // Overload #2 - different parameter list, same method name
    static void process(Account account, double bonusAmount) {
        System.out.println("Processing account " + account.getAccountNumber() +
                " with a bonus of " + bonusAmount);
        account.deposit(bonusAmount);
    }

    public static void main(String[] args) {
        Customer c1 = new Customer("CUST-01", "Ishaan Verma", "ishaan@example.com");
        Customer c2 = new Customer("CUST-02", "Priya Nair", "priya@example.com");

        // Both stored as the parent type "Account" - runtime polymorphism setup
        Account[] accounts = {
                new SavingsAccount("SB-2001", c1, 5000.0),
                new CurrentAccount("CA-3001", c2, 2000.0)
        };

        for (Account account : accounts) {
            // Same line of code, different behaviour depending on actual object type
            account.applyMonthlyMaintenance();
            System.out.println(account.describe() + " -> balance now " + account.getBalance());
        }

        // Overload resolution happens at compile time
        process(accounts[0]);
        process(accounts[1], 100.0);
    }
}
