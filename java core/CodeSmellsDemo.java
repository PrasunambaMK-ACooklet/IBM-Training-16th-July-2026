package com.bank.cleancode;

/**
 * TOPIC: Avoiding Code Smells (Duplication, Long Methods, God Classes)
 *
 * A "code smell" isn't a bug - the code still works - but it signals a
 * design problem that will make future changes slow and risky.
 */
public class CodeSmellsDemo {

    // ---------------- SMELL 1: Duplication ----------------
    static class Duplicated {
        double savingsInterest(double balance) {
            double rate = 4.0;
            return balance * rate / 100.0 / 12.0; // repeated formula
        }

        double fixedDepositInterest(double balance) {
            double rate = 7.5;
            return balance * rate / 100.0 / 12.0; // same formula, copy-pasted
        }
    }

    // FIX: extract the shared formula into ONE place; each caller only supplies what differs.
    static class DryVersion {
        static double monthlyInterest(double balance, double annualRatePercent) {
            return balance * annualRatePercent / 100.0 / 12.0;
        }

        double savingsInterest(double balance) {
            return monthlyInterest(balance, 4.0);
        }

        double fixedDepositInterest(double balance) {
            return monthlyInterest(balance, 7.5);
        }
    }

    // ---------------- SMELL 2: Long Method ----------------
    // (See SingleResponsibilityDemo.processWithdrawalBadly for a concrete
    // long-method example and its fix - a long method is usually just
    // several single-responsibility methods glued together.)

    // ---------------- SMELL 3: God Class ----------------
    // A single class trying to own EVERYTHING: accounts, transactions,
    // notifications, reporting, authentication... becomes impossible to
    // maintain because every unrelated change touches the same file.
    static class GodBankClass {
        void createAccount() { /* ... */ }
        void closeAccount() { /* ... */ }
        void processDeposit() { /* ... */ }
        void processWithdrawal() { /* ... */ }
        void sendSmsAlert() { /* ... */ }
        void sendEmailReceipt() { /* ... */ }
        void generateMonthlyReport() { /* ... */ }
        void authenticateUser() { /* ... */ }
        void encryptPassword() { /* ... */ }
        // ... dozens more unrelated methods pile up here over time
    }

    // FIX: split by responsibility - each class owns exactly one concern.
    // This mirrors how this whole project is actually organised into
    // separate packages: com.bank.core (accounts), com.bank.events
    // (notifications), com.bank.threading, com.bank.io, etc.
    static class AccountService {
        void createAccount() { /* ... */ }
        void closeAccount() { /* ... */ }
    }

    static class TransactionService {
        void processDeposit() { /* ... */ }
        void processWithdrawal() { /* ... */ }
    }

    static class NotificationService {
        void sendSmsAlert() { /* ... */ }
        void sendEmailReceipt() { /* ... */ }
    }

    public static void main(String[] args) {
        Duplicated duplicated = new Duplicated();
        System.out.println("Duplicated formula results: " +
                duplicated.savingsInterest(10000) + ", " + duplicated.fixedDepositInterest(10000));

        DryVersion dry = new DryVersion();
        System.out.println("DRY (single formula) results: " +
                dry.savingsInterest(10000) + ", " + dry.fixedDepositInterest(10000));

        System.out.println("God class and its split-up replacement compiled successfully - " +
                "see source comments for the design discussion.");
    }
}
