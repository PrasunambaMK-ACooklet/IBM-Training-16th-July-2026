package com.bank.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * TOPIC: Executor Framework Quick Start
 *
 * Instead of manually creating and managing raw Thread objects, the
 * Executor framework hands out a pool of worker threads and reuses them,
 * which is far more efficient for processing many short-lived tasks such
 * as validating a batch of transactions.
 */
public class ExecutorFrameworkDemo {

    // Simulates checking one transaction against a fraud-detection rule.
    static Callable<String> fraudCheckTask(int transactionId, double amount) {
        return () -> {
            Thread.sleep(50); // simulate some work
            String verdict = amount > 200000 ? "FLAGGED" : "CLEAR";
            return "Transaction #" + transactionId + " (" + amount + ") -> " + verdict;
        };
    }

    public static void main(String[] args) throws Exception {
        // A fixed-size pool of 4 worker threads shared by all submitted tasks
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        double[] transactionAmounts = {5000, 250000, 12000, 999999, 300};
        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < transactionAmounts.length; i++) {
            Future<String> future = executorService.submit(fraudCheckTask(i + 1, transactionAmounts[i]));
            results.add(future);
        }

        // Future.get() blocks until that specific task's result is ready
        for (Future<String> future : results) {
            System.out.println(future.get());
        }

        // Always shut the pool down, or the JVM won't exit
        executorService.shutdown();
        boolean finishedInTime = executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("All fraud checks completed in time: " + finishedInTime);
    }
}
