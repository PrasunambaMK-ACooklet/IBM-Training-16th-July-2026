package com.bank.innerclasses;

/**
 * TOPIC: Inner classes
 *
 * Java supports four flavours of "class inside a class":
 *   1. Member (non-static) inner class - tied to an instance of the outer
 *      class, can access the outer object's fields directly.
 *   2. Static nested class - does NOT need an outer instance, behaves like
 *      a regular top-level class that's just namespaced inside another.
 *   3. Local class - declared inside a method body, visible only there.
 *   4. Anonymous class - a one-off, unnamed class, usually implementing an
 *      interface on the spot.
 */
public class InnerClassDemo {

    private String accountNumber = "SB-4501";
    private double balance = 25000.0;

    // 1) Member (non-static) inner class - needs an outer BankStatement instance.
    class StatementLine {
        String describe() {
            // Can directly read the outer instance's private fields.
            return "Statement for " + accountNumber + " | current balance: " + balance;
        }
    }

    // 2) Static nested class - independent of any outer instance.
    static class InterestCalculator {
        static double calculate(double principal, double annualRatePercent) {
            return principal * annualRatePercent / 100.0;
        }
    }

    void demonstrateLocalClass() {
        double bonusPercent = 2.0;

        // 3) Local class - defined right here, only usable inside this method.
        class BonusApplier {
            void apply() {
                double bonus = balance * bonusPercent / 100.0;
                balance += bonus;
                System.out.println("Local class applied bonus: " + bonus + ", new balance: " + balance);
            }
        }

        new BonusApplier().apply();
    }

    interface NotificationSender {
        void send(String message);
    }

    void demonstrateAnonymousClass() {
        // 4) Anonymous class - implements NotificationSender inline, no separate file needed.
        NotificationSender emailSender = new NotificationSender() {
            @Override
            public void send(String message) {
                System.out.println("EMAIL to account " + accountNumber + ": " + message);
            }
        };
        emailSender.send("Your monthly statement is ready.");
    }

    public static void main(String[] args) {
        InnerClassDemo outer = new InnerClassDemo();

        // Member inner class needs an outer instance to be created:
        InnerClassDemo.StatementLine line = outer.new StatementLine();
        System.out.println(line.describe());

        // Static nested class is used just like a normal class:
        double interest = InterestCalculator.calculate(50000, 6.5);
        System.out.println("Calculated interest: " + interest);

        outer.demonstrateLocalClass();
        outer.demonstrateAnonymousClass();
    }
}
