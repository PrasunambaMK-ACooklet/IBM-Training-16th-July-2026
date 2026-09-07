package com.bank.basics;

/**
 * TOPIC: Static Members and their execution control flow
 *
 * Static fields/methods/blocks belong to the CLASS, not to any one object.
 * There is exactly one copy of a static field shared by every instance.
 *
 * EXECUTION ORDER when a class is first used:
 *   1. Static fields are allocated and given default values.
 *   2. Static field initializers and static blocks run, IN THE ORDER
 *      they appear in the source file, exactly once, the first time the
 *      class is loaded.
 *   3. Only after that can any instance (non-static) code run.
 */
public class StaticMembersDemo {

    // Static field: shared bank-wide counter, one copy total.
    private static int totalAccountsOpenedTodayCounter;

    // Static field with an initializer - runs during class loading.
    private static final String BANK_NAME = loadBankName();

    // Static block - also runs once during class loading, useful for
    // more complex setup than a single expression can express.
    static {
        System.out.println("[static block] Initializing " + BANK_NAME + " core banking module...");
        totalAccountsOpenedTodayCounter = 0;
    }

    private static String loadBankName() {
        System.out.println("[static initializer] loading bank name constant");
        return "Acme National Bank";
    }

    // Static method: called on the class itself, e.g. StaticMembersDemo.openAccount()
    public static void openAccount(String customerName) {
        totalAccountsOpenedTodayCounter++;
        System.out.println("Opened account #" + totalAccountsOpenedTodayCounter + " for " + customerName);
    }

    public static int getTotalAccountsOpenedToday() {
        return totalAccountsOpenedTodayCounter;
    }

    public static void main(String[] args) {
        System.out.println("main() starts running only AFTER static init above.");
        StaticMembersDemo.openAccount("Asha Rao");
        StaticMembersDemo.openAccount("Vikram Shah");
        System.out.println("Total accounts opened today: " + StaticMembersDemo.getTotalAccountsOpenedToday());
    }
}
