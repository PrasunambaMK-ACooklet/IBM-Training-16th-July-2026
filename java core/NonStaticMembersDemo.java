package com.bank.basics;

/**
 * TOPIC: Non-Static Members and their execution control flow
 *
 * Instance (non-static) fields belong to each OBJECT separately - every
 * "new BankTeller(...)" gets its own copy of name/customersServedCount.
 *
 * EXECUTION ORDER every time "new" is called:
 *   1. Instance fields get default values.
 *   2. Instance field initializers and instance initializer blocks run,
 *      in source order.
 *   3. The constructor body runs last.
 *   This whole sequence repeats for every single object created.
 */
class BankTeller {

    private final String name;
    private int customersServedCount = 0; // instance field initializer

    // Instance initializer block - runs before the constructor body,
    // every time an object is created.
    {
        System.out.println("[instance initializer block] preparing a new teller...");
    }

    public BankTeller(String name) {
        System.out.println("[constructor] Teller " + name + " has clocked in.");
        this.name = name;
    }

    public void serveCustomer(String customerName) {
        customersServedCount++;
        System.out.println(name + " served " + customerName +
                " (total served by " + name + ": " + customersServedCount + ")");
    }
}

public class NonStaticMembersDemo {
    public static void main(String[] args) {
        BankTeller teller1 = new BankTeller("Meera"); // full init sequence runs
        BankTeller teller2 = new BankTeller("Arjun"); // runs again, independently

        teller1.serveCustomer("Customer A");
        teller1.serveCustomer("Customer B");
        teller2.serveCustomer("Customer C");
        // teller1 and teller2 have their own independent customersServedCount
    }
}
