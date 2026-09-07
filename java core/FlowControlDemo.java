package com.bank.basics;

/**
 * TOPIC: Flow Control Statements
 * Covers if/else, switch (classic and modern arrow form), for, while,
 * do-while, and break/continue with a labeled loop.
 */
public class FlowControlDemo {

    public static void main(String[] args) {
        double balance = 42000;

        // ---- if / else if / else ----
        String tier;
        if (balance >= 100000) {
            tier = "PLATINUM";
        } else if (balance >= 50000) {
            tier = "GOLD";
        } else if (balance >= 10000) {
            tier = "SILVER";
        } else {
            tier = "BASIC";
        }
        System.out.println("Customer tier: " + tier);

        // ---- classic switch statement (falls through unless you break) ----
        int month = 3;
        int daysInMonth;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysInMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysInMonth = 30;
                break;
            case 2:
                daysInMonth = 28;
                break;
            default:
                daysInMonth = -1;
        }
        System.out.println("Days in month " + month + ": " + daysInMonth);

        // ---- modern switch expression (Java 14+, arrow form, no fall-through) ----
        String tierBenefit = switch (tier) {
            case "PLATINUM" -> "Free locker + dedicated relationship manager";
            case "GOLD" -> "Free locker";
            case "SILVER" -> "Reduced transaction fees";
            default -> "Standard benefits";
        };
        System.out.println("Benefit: " + tierBenefit);

        // ---- for loop: apply interest for 12 months ----
        double runningBalance = balance;
        double monthlyRate = 0.005;
        for (int m = 1; m <= 12; m++) {
            runningBalance += runningBalance * monthlyRate;
        }
        System.out.printf("Balance after 12 months of compounding: %.2f%n", runningBalance);

        // ---- while loop: keep withdrawing a fixed EMI until balance is too low ----
        double emi = 5000;
        double loanBalance = 23000;
        int installmentsPaid = 0;
        while (loanBalance >= emi) {
            loanBalance -= emi;
            installmentsPaid++;
        }
        System.out.println("Installments fully paid: " + installmentsPaid + ", remainder: " + loanBalance);

        // ---- do-while loop: guaranteed to run at least once (e.g. PIN retry) ----
        int attempt = 0;
        int correctPin = 4321;
        int[] pinAttempts = {1111, 2222, 4321, 9999};
        boolean authenticated = false;
        do {
            int entered = pinAttempts[attempt];
            authenticated = entered == correctPin;
            attempt++;
        } while (!authenticated && attempt < pinAttempts.length);
        System.out.println("Authenticated: " + authenticated + " after " + attempt + " attempt(s)");

        // ---- break / continue with a labeled outer loop ----
        int[][] branchTransactionMatrix = {
                {100, 200, -1},
                {50, -1, 300},
                {400, 500, 600}
        };
        outerScan:
        for (int row = 0; row < branchTransactionMatrix.length; row++) {
            for (int col = 0; col < branchTransactionMatrix[row].length; col++) {
                int value = branchTransactionMatrix[row][col];
                if (value < 0) {
                    continue; // skip an invalid/void transaction entry
                }
                if (value >= 500) {
                    System.out.println("Found large transaction " + value + " at [" + row + "][" + col + "], stopping scan");
                    break outerScan; // exits BOTH loops at once
                }
                System.out.println("Checked transaction: " + value);
            }
        }
    }
}
