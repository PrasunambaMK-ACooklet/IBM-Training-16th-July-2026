package com.bank.threading;

import com.bank.core.Account;
import com.bank.core.SavingsAccount;
import com.bank.model.Customer;

/**
 * TOPIC: Multithreading
 *
 * Demonstrates creating threads two ways (extending Thread and implementing
 * Runnable), joining them, and using "synchronized" to protect a shared
 * account balance from a classic race condition (two threads reading and
 * writing balance at the same time).
 */
class DepositTask implements Runnable {
    private final Account account;
    private final double amount;
    private final int times;

    DepositTask(Account account, double amount, int times) {
        this.account = account;
        this.amount = amount;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            synchronized (account) { // only one thread can execute this block per account at a time
                account.deposit(amount);
            }
        }
    }
}

public class MultithreadingDemo {

    public static void main(String[] args) throws InterruptedException {
        Customer customer = new Customer("CUST-77", "Neha Kulkarni", "neha@example.com");
        Account sharedAccount = new SavingsAccount("SB-7777", customer, 0.0);

        // Two threads both deposit into the SAME account concurrently.
        Thread thread1 = new Thread(new DepositTask(sharedAccount, 10.0, 1000), "Deposit-Thread-1");
        Thread thread2 = new Thread(new DepositTask(sharedAccount, 10.0, 1000), "Deposit-Thread-2");

        System.out.println("Starting concurrent deposits...");
        thread1.start();
        thread2.start();

        // join() makes the main thread wait until both worker threads finish
        thread1.join();
        thread2.join();

        // Thanks to "synchronized", this is always exactly 20000.0 - without
        // synchronization, lost updates would make this unpredictable.
        System.out.println("Final balance (expected 20000.0): " + sharedAccount.getBalance());

        // A thread created by extending Thread directly
        Thread reportThread = new Thread("Report-Thread") {
            @Override
            public void run() {
                System.out.println(getName() + " generating end-of-day report...");
            }
        };
        reportThread.start();
        reportThread.join();

        System.out.println("Main thread finished.");
    }
}
