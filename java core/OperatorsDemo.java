package com.bank.basics;

/**
 * TOPIC: Operators and Assignments
 */
public class OperatorsDemo {

    public static void main(String[] args) {
        double principal = 100000;
        double rate = 8.5;
        int years = 2;

        // ---- arithmetic operators ----
        double simpleInterest = (principal * rate * years) / 100;
        System.out.println("Simple interest: " + simpleInterest);

        int transactionsThisMonth = 47;
        int freeLimit = 10;
        int chargeableTransactions = transactionsThisMonth - freeLimit;
        int feePerTransaction = 5;
        int totalFee = chargeableTransactions * feePerTransaction;
        int remainder = transactionsThisMonth % freeLimit; // modulus
        System.out.println("Chargeable: " + chargeableTransactions + ", fee: " + totalFee +
                ", remainder when grouped by " + freeLimit + ": " + remainder);

        // ---- compound assignment operators ----
        double balance = 5000;
        balance += 1500;  // deposit
        balance -= 200;   // service charge
        balance *= 1.001; // tiny bonus multiplier
        System.out.println("Balance after compound assignments: " + balance);

        // ---- increment / decrement (pre vs post) ----
        int loyaltyPoints = 10;
        int postIncrement = loyaltyPoints++; // uses 10, THEN increments
        int preIncrement = ++loyaltyPoints;  // increments FIRST, then uses 12
        System.out.println("post=" + postIncrement + " pre=" + preIncrement + " final=" + loyaltyPoints);

        // ---- relational operators ----
        boolean isEligibleForLoan = balance > 3000 && years >= 1;
        boolean isHighRisk = transactionsThisMonth > 40 || rate < 5;
        boolean isExactMatch = (int) balance == 6301; // equality

        // ---- logical operators (&&, ||, ! with short-circuit evaluation) ----
        System.out.println("Eligible for loan: " + isEligibleForLoan);
        System.out.println("High risk flag: " + isHighRisk);
        System.out.println("Not high risk: " + !isHighRisk);
        System.out.println("Exact match check: " + isExactMatch);

        // ---- ternary (conditional) operator ----
        String accountStatus = balance >= 1000 ? "ACTIVE" : "BELOW_MINIMUM";
        System.out.println("Account status: " + accountStatus);

        // ---- bitwise operators (rarely needed in banking, shown for completeness) ----
        int permissionFlags = 0b0110; // e.g. bit flags for VIEW|TRANSFER
        int adminFlag = 0b1000;
        int combinedFlags = permissionFlags | adminFlag; // bitwise OR
        int commonFlags = permissionFlags & adminFlag;   // bitwise AND
        System.out.println("Combined flags: " + combinedFlags + ", common flags: " + commonFlags);
    }
}
