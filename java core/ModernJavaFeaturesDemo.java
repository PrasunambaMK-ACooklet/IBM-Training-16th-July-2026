package com.bank.modern;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TOPIC: Java 17 and 21 LTS Features
 * (Records, Sealed Classes, Pattern Matching for Switch, Virtual Threads)
 */
public class ModernJavaFeaturesDemo {

    // ---- RECORD (Java 16+) ----
    // A compact, immutable data carrier. The compiler auto-generates the
    // constructor, accessors (amount(), currency()...), equals(), hashCode()
    // and toString() - no boilerplate needed.
    record Money(double amount, String currency) {
        Money {
            if (amount < 0) {
                throw new IllegalArgumentException("Money amount cannot be negative");
            }
        }
    }

    // ---- SEALED CLASSES (Java 17) ----
    // A sealed interface/class explicitly lists every class allowed to
    // implement/extend it. This lets the compiler verify a switch over
    // its subtypes is exhaustive, which plain interfaces cannot guarantee.
    sealed interface PaymentInstruction permits CardPayment, BankTransfer, UpiPayment {}

    record CardPayment(String maskedCardNumber, Money amount) implements PaymentInstruction {}
    record BankTransfer(String beneficiaryAccount, Money amount) implements PaymentInstruction {}
    record UpiPayment(String upiId, Money amount) implements PaymentInstruction {}

    // ---- PATTERN MATCHING FOR SWITCH (Java 21) ----
    // The switch expression can match on the RUNTIME TYPE of the sealed
    // interface, binding a typed variable per branch - no manual casting.
    // Because PaymentInstruction is sealed, the compiler can confirm every
    // permitted subtype is handled, so no "default" branch is required.
    static String describe(PaymentInstruction instruction) {
        return switch (instruction) {
            case CardPayment card ->
                    "Card payment of " + card.amount().amount() + " " + card.amount().currency() +
                            " using card " + card.maskedCardNumber();
            case BankTransfer transfer ->
                    "Bank transfer of " + transfer.amount().amount() + " " + transfer.amount().currency() +
                            " to account " + transfer.beneficiaryAccount();
            case UpiPayment upi ->
                    "UPI payment of " + upi.amount().amount() + " " + upi.amount().currency() +
                            " via " + upi.upiId();
        };
    }

    // Pattern matching for instanceof (Java 16+) - no separate cast needed.
    static boolean isLargeCardPayment(Object obj) {
        return obj instanceof CardPayment card && card.amount().amount() > 100000;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<PaymentInstruction> instructions = List.of(
                new CardPayment("**** **** **** 4242", new Money(2500.0, "INR")),
                new BankTransfer("SB-1001", new Money(150000.0, "INR")),
                new UpiPayment("ramesh@upi", new Money(499.0, "INR"))
        );

        for (PaymentInstruction instruction : instructions) {
            System.out.println(describe(instruction));
            System.out.println("  Is large card payment? " + isLargeCardPayment(instruction));
        }

        // ---- VIRTUAL THREADS (Java 21) ----
        // Lightweight threads managed by the JVM (not the OS), ideal for
        // spinning up thousands of concurrent, mostly-I/O-bound tasks (like
        // processing a huge batch of payments) far more cheaply than
        // platform threads.
        try (ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures = instructions.stream()
                    .map(instruction -> virtualThreadExecutor.submit(() -> {
                        Thread.sleep(50); // simulate I/O, e.g. calling a payment gateway
                        return Thread.currentThread() + " processed: " + describe(instruction);
                    }))
                    .toList();

            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        }
    }
}
