package com.bank.arrays;

import java.util.Arrays;

/**
 * TOPIC: Arrays
 *
 * Arrays are fixed-size, ordered collections of a single type. Covers
 * 1-D and 2-D arrays, iteration, sorting/searching, and the java.util.Arrays
 * helper methods.
 */
public class ArraysDemo {

    public static void main(String[] args) {
        // ---- 1-D array: fixed size declared up front ----
        double[] dailyClosingBalances = new double[5];
        dailyClosingBalances[0] = 10500.0;
        dailyClosingBalances[1] = 10800.0;
        dailyClosingBalances[2] = 9800.0;
        dailyClosingBalances[3] = 11200.0;
        dailyClosingBalances[4] = 11000.0;

        // ---- array literal shorthand ----
        String[] branchCodes = {"MUM01", "DEL02", "BLR03", "CHN04"};

        // ---- enhanced for loop ----
        double sum = 0;
        for (double balance : dailyClosingBalances) {
            sum += balance;
        }
        double average = sum / dailyClosingBalances.length;
        System.out.printf("Average closing balance: %.2f%n", average);

        // ---- java.util.Arrays helper methods ----
        double[] sortedCopy = Arrays.copyOf(dailyClosingBalances, dailyClosingBalances.length);
        Arrays.sort(sortedCopy);
        System.out.println("Sorted balances: " + Arrays.toString(sortedCopy));

        int index = Arrays.binarySearch(sortedCopy, 11000.0);
        System.out.println("Index of 11000.0 in sorted array: " + index);

        double[] filledArray = new double[5];
        Arrays.fill(filledArray, 100.0);
        System.out.println("Filled array: " + Arrays.toString(filledArray));

        boolean sameContent = Arrays.equals(dailyClosingBalances, dailyClosingBalances.clone());
        System.out.println("Array equals its own clone (by content): " + sameContent);

        // ---- 2-D array: branch x quarter transaction totals ----
        double[][] branchQuarterlyTotals = {
                {150000, 162000, 158000, 171000}, // MUM01
                {98000, 102000, 99500, 110000},   // DEL02
                {120000, 121000, 130500, 128000}  // BLR03
        };

        for (int branch = 0; branch < branchQuarterlyTotals.length; branch++) {
            double branchTotal = 0;
            for (int quarter = 0; quarter < branchQuarterlyTotals[branch].length; quarter++) {
                branchTotal += branchQuarterlyTotals[branch][quarter];
            }
            System.out.printf("Branch %s yearly total: %.2f%n", branchCodes[branch], branchTotal);
        }

        System.out.println("2-D array pretty-printed: " + Arrays.deepToString(branchQuarterlyTotals));
    }
}
