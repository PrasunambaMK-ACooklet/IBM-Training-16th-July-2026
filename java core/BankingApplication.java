package com.bank;

import com.bank.core.Account;
import com.bank.core.CurrentAccount;
import com.bank.core.SavingsAccount;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.model.Customer;

/**
 * TOPIC: Introduction to Java and OOPS (Object-Oriented Programming)
 *
 * This is the main entry point that ties the whole Banking Application
 * project together. Java is a class-based, OBJECT-ORIENTED language built
 * on four pillars, each demonstrated concretely elsewhere in this project:
 *
 *   ENCAPSULATION  - com.bank.core.Account keeps its balance private and
 *                    only changeable through controlled methods.
 *   ABSTRACTION    - com.bank.core.Account is an abstract class defining
 *                    WHAT an account can do, not HOW every type does it.
 *   INHERITANCE    - SavingsAccount and CurrentAccount both extend Account,
 *                    reusing its common behaviour.
 *   POLYMORPHISM   - the loop below calls applyMonthlyMaintenance() on an
 *                    Account reference, but the SUBCLASS's version runs.
 *
 * Every other topic in the syllabus (collections, streams, threads, I/O,
 * annotations, records, sealed classes, SOLID principles, etc.) has its
 * own focused, runnable demo class in a dedicated package under
 * src/com/bank/ - see PROJECT_TOPIC_INDEX.md for the full map.
 */
public class BankingApplication {

    public static void main(String[] args) {
        System.out.println("=== Acme National Bank - Core Banking Application ===\n");

        // Object creation: instantiating real objects from our classes.
        Customer customer1 = new Customer("CUST-100", "Aditi Sharma", "aditi@example.com");
        Customer customer2 = new Customer("CUST-101", "Rohan Gupta", "rohan@example.com");

        Account savings = new SavingsAccount("SB-5001", customer1, 20000.0);
        Account current = new CurrentAccount("CA-6001", customer2, 5000.0);

        Account[] allAccounts = { savings, current };

        System.out.println("Accounts created so far (static counter): " + Account.getAccountsCreatedCount());

        for (Account account : allAccounts) {
            System.out.println("\n" + account);
        }

        System.out.println("\n--- Performing a deposit and a withdrawal ---");
        savings.deposit(2500.0);
        try {
            current.withdraw(8000.0); // allowed thanks to CurrentAccount's overdraft rule
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }

        System.out.println("\n--- End of month maintenance (polymorphism in action) ---");
        for (Account account : allAccounts) {
            account.applyMonthlyMaintenance(); // runs a different implementation per subclass
        }

        System.out.println("\n--- Final balances ---");
        for (Account account : allAccounts) {
            System.out.println(account);
        }

        System.out.println("\nExplore the other packages under com.bank.* for every other topic demo.");
    }
}
