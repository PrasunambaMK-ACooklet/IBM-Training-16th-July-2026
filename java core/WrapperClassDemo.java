package com.bank.basics;

import java.util.ArrayList;
import java.util.List;

/**
 * TOPIC: Wrapper Classes with Auto boxing & unboxing
 *
 * Every primitive has a corresponding wrapper class (int -> Integer,
 * double -> Double, boolean -> Boolean, ...). Wrappers are needed wherever
 * an Object is required, e.g. inside collections like List<Integer>.
 */
public class WrapperClassDemo {

    public static void main(String[] args) {
        // Autoboxing: primitive int automatically wrapped into an Integer object
        Integer accountNumberObj = 100234567;

        // Unboxing: Integer object automatically converted back to primitive int
        int accountNumberPrimitive = accountNumberObj;

        System.out.println("Boxed: " + accountNumberObj + ", unboxed: " + accountNumberPrimitive);

        // Collections can only store objects, so autoboxing happens implicitly here
        List<Double> monthlyDeposits = new ArrayList<>();
        monthlyDeposits.add(1500.0);  // double autoboxed to Double
        monthlyDeposits.add(2300.50);
        monthlyDeposits.add(999.99);

        double total = 0; // unboxing happens on every read inside the loop
        for (Double deposit : monthlyDeposits) {
            total += deposit;
        }
        System.out.println("Total deposits: " + total);

        // Useful wrapper class utilities
        String balanceText = "45230.75";
        double parsedBalance = Double.parseDouble(balanceText); // String -> primitive
        System.out.println("Parsed balance: " + parsedBalance);

        int minTransferUnit = Integer.MIN_VALUE;
        int maxTransferUnit = Integer.MAX_VALUE;
        System.out.println("Integer range: " + minTransferUnit + " to " + maxTransferUnit);

        // CAUTION: Integer caching pitfall. Values from -128 to 127 are cached and
        // shared, so == works "by accident"; outside that range == compares
        // references, not values - always use .equals() for wrapper comparisons.
        Integer smallA = 100;
        Integer smallB = 100;
        Integer bigA = 200;
        Integer bigB = 200;
        System.out.println("100 == 100 (cached): " + (smallA == smallB));
        System.out.println("200 == 200 (not cached): " + (bigA == bigB));
        System.out.println("200.equals(200) (always correct): " + bigA.equals(bigB));

        // Autoboxing pitfall: unboxing a null wrapper throws NullPointerException
        Integer possiblyNullBonus = null;
        try {
            int bonus = possiblyNullBonus; // auto-unboxing null -> NPE
            System.out.println(bonus);
        } catch (NullPointerException e) {
            System.out.println("Caught NPE from unboxing a null Integer - always null-check wrappers!");
        }
    }
}
