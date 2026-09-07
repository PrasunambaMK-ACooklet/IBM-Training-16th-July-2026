package com.bank.objectmethods;

import java.util.HashSet;
import java.util.Set;

/**
 * TOPIC: Object class methods
 */
public class ObjectMethodsDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Transaction t1 = new Transaction("TXN-001", "SB-1001", 500.0);
        Transaction t2 = new Transaction("TXN-001", "SB-1001", 500.0); // same id, "equal" by our rule
        Transaction t3 = new Transaction("TXN-002", "SB-1001", 750.0); // different id

        // toString() override in action
        System.out.println("t1 = " + t1);

        // equals()/hashCode() override in action
        System.out.println("t1.equals(t2): " + t1.equals(t2)); // true - same business id
        System.out.println("t1.equals(t3): " + t1.equals(t3)); // false
        System.out.println("t1 == t2 (reference equality): " + (t1 == t2)); // false - different objects
        System.out.println("t1.hashCode() == t2.hashCode(): " + (t1.hashCode() == t2.hashCode())); // true

        // Because equals()/hashCode() are consistent, a HashSet correctly
        // treats t1 and t2 as duplicates.
        Set<Transaction> uniqueTransactions = new HashSet<>();
        uniqueTransactions.add(t1);
        uniqueTransactions.add(t2);
        uniqueTransactions.add(t3);
        System.out.println("Unique transactions stored (expect 2): " + uniqueTransactions.size());

        // getClass() - reflection basics inherited from Object
        System.out.println("Runtime class: " + t1.getClass().getName());

        // Object also provides wait()/notify()/notifyAll() for thread coordination
        // and clone() for object copying (not used here to keep things simple).
    }
}
