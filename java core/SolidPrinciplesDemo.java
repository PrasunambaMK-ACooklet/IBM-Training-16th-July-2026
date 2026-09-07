package com.bank.cleancode;

/**
 * TOPIC: Basic SOLID Principles Overview
 *
 * S - Single Responsibility : a class should have only one reason to change.
 * O - Open/Closed            : open for extension, closed for modification.
 * L - Liskov Substitution    : a subtype must be usable wherever its parent is expected.
 * I - Interface Segregation  : prefer several small interfaces over one fat one.
 * D - Dependency Inversion   : depend on abstractions, not concrete implementations.
 */
public class SolidPrinciplesDemo {

    // ---------------- S: Single Responsibility Principle ----------------
    // InterestCalculator ONLY calculates interest; it doesn't also send
    // emails or write to a database. (Also demonstrated in CodeSmellsDemo.)
    static class InterestCalculator {
        double calculate(double balance, double annualRatePercent) {
            return balance * annualRatePercent / 100.0;
        }
    }

    // ---------------- O: Open/Closed Principle ----------------
    // FeeStrategy lets us ADD new fee rules by writing a NEW class, without
    // modifying any existing, already-tested fee class.
    interface FeeStrategy {
        double calculateFee(double amount);
    }

    static class FlatFeeStrategy implements FeeStrategy {
        public double calculateFee(double amount) {
            return 25.0;
        }
    }

    static class PercentageFeeStrategy implements FeeStrategy {
        public double calculateFee(double amount) {
            return amount * 0.01;
        }
    }
    // Adding, say, TieredFeeStrategy later requires zero changes above -
    // that's "closed for modification, open for extension".

    // ---------------- L: Liskov Substitution Principle ----------------
    // Any code written against the Account abstraction (see com.bank.core)
    // must keep working correctly no matter which subclass (SavingsAccount,
    // CurrentAccount) is actually substituted in. A violation would be a
    // subclass that, say, silently ignores withdraw() or throws for no reason.
    interface WithdrawableAccount {
        void withdraw(double amount);
    }

    static class WellBehavedSavings implements WithdrawableAccount {
        double balance = 1000;
        public void withdraw(double amount) {
            if (amount <= balance) balance -= amount;
        }
    }
    // A BAD subclass would be one whose withdraw() always throws
    // UnsupportedOperationException - that would violate LSP because it
    // can no longer be used wherever a WithdrawableAccount is expected.

    // ---------------- I: Interface Segregation Principle ----------------
    // BAD: one fat interface forces every account type to implement methods
    // that don't apply to it (e.g. a savings account has no overdraft).
    interface FatAccountOperations {
        void deposit(double amount);
        void withdraw(double amount);
        void requestOverdraft(double amount); // not every account supports this!
        void payInterest();                    // not every account earns interest!
    }

    // GOOD: split into small, focused interfaces; a class implements only
    // what genuinely applies to it.
    interface Depositable {
        void deposit(double amount);
    }

    interface Withdrawable {
        void withdraw(double amount);
    }

    interface OverdraftCapable {
        void requestOverdraft(double amount);
    }

    interface InterestBearing {
        void payInterest();
    }

    static class SegregatedSavingsAccount implements Depositable, Withdrawable, InterestBearing {
        public void deposit(double amount) { }
        public void withdraw(double amount) { }
        public void payInterest() { }
        // no requestOverdraft() forced on it - it simply doesn't implement OverdraftCapable
    }

    // ---------------- D: Dependency Inversion Principle ----------------
    // BAD: NotificationServiceBad is hard-wired to one concrete sender.
    static class EmailSender {
        void send(String message) {
            System.out.println("EMAIL: " + message);
        }
    }

    static class NotificationServiceBad {
        private final EmailSender emailSender = new EmailSender(); // hard dependency
        void notifyCustomer(String message) {
            emailSender.send(message);
        }
    }

    // GOOD: depend on an abstraction; the concrete implementation is
    // injected from outside, so swapping Email for SMS needs no change here.
    interface MessageSender {
        void send(String message);
    }

    static class SmsSender implements MessageSender {
        public void send(String message) {
            System.out.println("SMS: " + message);
        }
    }

    static class EmailMessageSender implements MessageSender {
        public void send(String message) {
            System.out.println("EMAIL: " + message);
        }
    }

    static class NotificationServiceGood {
        private final MessageSender messageSender; // depends on the abstraction

        NotificationServiceGood(MessageSender messageSender) { // injected
            this.messageSender = messageSender;
        }

        void notifyCustomer(String message) {
            messageSender.send(message);
        }
    }

    public static void main(String[] args) {
        InterestCalculator calculator = new InterestCalculator();
        System.out.println("SRP - interest: " + calculator.calculate(10000, 5));

        FeeStrategy strategy = new PercentageFeeStrategy();
        System.out.println("OCP - fee via strategy: " + strategy.calculateFee(5000));

        WithdrawableAccount account = new WellBehavedSavings();
        account.withdraw(200);
        System.out.println("LSP - substitution works transparently through the interface");

        SegregatedSavingsAccount segregated = new SegregatedSavingsAccount();
        segregated.deposit(100);
        System.out.println("ISP - class only implements the interfaces relevant to it");

        NotificationServiceGood withSms = new NotificationServiceGood(new SmsSender());
        NotificationServiceGood withEmail = new NotificationServiceGood(new EmailMessageSender());
        withSms.notifyCustomer("Your OTP is 483920");
        withEmail.notifyCustomer("Your statement is ready");
        System.out.println("DIP - same service class, two different senders injected from outside");
    }
}
