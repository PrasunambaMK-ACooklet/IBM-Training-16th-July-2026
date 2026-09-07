package com.bank.cleancode;

/**
 * TOPIC: Functions: Single Responsibility & Keeping Them Small
 *
 * A function should do ONE thing, do it well, and do it alone. When a
 * function grows to handle validation AND calculation AND printing AND
 * persistence, it becomes hard to test, reuse, and change safely.
 */
public class SingleResponsibilityDemo {

    // ---------------- BAD: one giant function doing five different jobs ----------------
    static void processWithdrawalBadly(double balance, double amount) {
        // 1. validation
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }
        // 2. business rule check
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return;
        }
        // 3. calculation
        double newBalance = balance - amount;
        // 4. fee calculation mixed in
        double fee = amount > 10000 ? 50 : 0;
        newBalance -= fee;
        // 5. formatting/output mixed in too
        System.out.println("Withdrew " + amount + ", fee " + fee + ", new balance: " + newBalance);
    }

    // ---------------- GOOD: each function has exactly one reason to change ----------------
    static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    static boolean hasSufficientFunds(double balance, double amount) {
        return amount <= balance;
    }

    static double calculateWithdrawalFee(double amount) {
        return amount > 10000 ? 50 : 0;
    }

    static double calculateNewBalance(double balance, double amount, double fee) {
        return balance - amount - fee;
    }

    static void printWithdrawalSummary(double amount, double fee, double newBalance) {
        System.out.println("Withdrew " + amount + ", fee " + fee + ", new balance: " + newBalance);
    }

    static void processWithdrawalCleanly(double balance, double amount) {
        if (!isValidAmount(amount)) {
            System.out.println("Invalid amount");
            return;
        }
        if (!hasSufficientFunds(balance, amount)) {
            System.out.println("Insufficient funds");
            return;
        }
        double fee = calculateWithdrawalFee(amount);
        double newBalance = calculateNewBalance(balance, amount, fee);
        printWithdrawalSummary(amount, fee, newBalance);
    }

    public static void main(String[] args) {
        System.out.println("--- Bad: one function doing everything ---");
        processWithdrawalBadly(20000, 15000);

        System.out.println("--- Good: composed from small, single-purpose functions ---");
        processWithdrawalCleanly(20000, 15000);

        // Benefit: each small function is independently testable, e.g.
        System.out.println("Fee for 15000 alone: " + calculateWithdrawalFee(15000));
        System.out.println("Fee for 5000 alone: " + calculateWithdrawalFee(5000));
    }
}
