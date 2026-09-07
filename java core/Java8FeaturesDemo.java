package com.bank.java8;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TOPIC: Java 8 Features
 *
 * Covers lambdas, method references, the Streams API, Optional, and
 * default/static interface methods - the biggest single upgrade in Java's
 * history at the time.
 */
public class Java8FeaturesDemo {

    record TransactionRecordLite(String accountNumber, double amount, String type) {}

    interface Greeter {
        String greet(String name);

        // default method: interfaces can now provide a body
        default String greetFormally(String name) {
            return "Dear " + greet(name);
        }

        // static method on an interface
        static Greeter standard() {
            return name -> "Hello, " + name;
        }
    }

    public static void main(String[] args) {
        List<TransactionRecordLite> transactions = List.of(
                new TransactionRecordLite("SB-1001", 5000, "DEPOSIT"),
                new TransactionRecordLite("SB-1001", -1200, "WITHDRAWAL"),
                new TransactionRecordLite("SB-1002", 3000, "DEPOSIT"),
                new TransactionRecordLite("SB-1002", -500, "WITHDRAWAL"),
                new TransactionRecordLite("SB-1003", 10000, "DEPOSIT")
        );

        // ---- Lambda expression assigned to a functional interface ----
        Function<Double, Double> applyTwoPercentFee = amount -> amount * 0.98;
        System.out.println("5000 after 2% fee: " + applyTwoPercentFee.apply(5000.0));

        // ---- Method reference ----
        Greeter greeter = Java8FeaturesDemo::simpleGreet;
        System.out.println(greeter.greet("Rahul"));
        System.out.println(greeter.greetFormally("Rahul")); // uses the default method
        System.out.println(Greeter.standard().greet("Priya")); // uses the static factory method

        // ---- Streams API: filter, map, reduce/collect ----
        double totalDeposits = transactions.stream()
                .filter(tx -> tx.type().equals("DEPOSIT"))
                .mapToDouble(TransactionRecordLite::amount)
                .sum();
        System.out.println("Total deposits across all accounts: " + totalDeposits);

        List<String> highValueAccounts = transactions.stream()
                .filter(tx -> tx.amount() > 4000)
                .map(TransactionRecordLite::accountNumber)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Accounts with a transaction over 4000: " + highValueAccounts);

        var totalsByAccount = transactions.stream()
                .collect(Collectors.groupingBy(TransactionRecordLite::accountNumber,
                        Collectors.summingDouble(TransactionRecordLite::amount)));
        System.out.println("Net movement per account: " + totalsByAccount);

        // ---- Optional: an explicit container for "value might be absent" ----
        Optional<TransactionRecordLite> firstBigDeposit = transactions.stream()
                .filter(tx -> tx.amount() >= 8000)
                .findFirst();
        firstBigDeposit.ifPresentOrElse(
                tx -> System.out.println("Found a big deposit: " + tx),
                () -> System.out.println("No deposit that large was found"));

        String description = firstBigDeposit
                .map(tx -> tx.accountNumber() + " received " + tx.amount())
                .orElse("Nothing to report");
        System.out.println(description);
    }

    static String simpleGreet(String name) {
        return "Hi " + name;
    }
}
