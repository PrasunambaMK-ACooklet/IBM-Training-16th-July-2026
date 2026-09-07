package com.bank.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * TOPIC: Collections with Generics
 *
 * Generics (the <Type> syntax) give compile-time type safety to
 * collections - the compiler rejects putting a String into a
 * List<Double> instead of failing at runtime with a ClassCastException.
 */
public class CollectionsDemo {

    // A small generic class of our own, independent of the built-in collections.
    static class Pair<A, B> {
        private final A first;
        private final B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A getFirst() { return first; }
        B getSecond() { return second; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static void main(String[] args) {
        // ---- List<T>: ordered, allows duplicates ----
        List<String> vipAccountNumbers = new ArrayList<>();
        vipAccountNumbers.add("SB-1001");
        vipAccountNumbers.add("SB-1002");
        vipAccountNumbers.add("SB-1001"); // duplicate allowed
        System.out.println("VIP list (with duplicate): " + vipAccountNumbers);

        List<String> queue = new LinkedList<>(vipAccountNumbers);
        Collections.reverse(queue);
        System.out.println("Reversed via LinkedList: " + queue);

        // ---- Set<T>: no duplicates ----
        Set<String> uniqueVipAccounts = new HashSet<>(vipAccountNumbers);
        System.out.println("Unique VIP accounts (duplicate removed): " + uniqueVipAccounts);

        // ---- Map<K,V>: key-value pairs ----
        Map<String, Double> accountBalances = new HashMap<>();
        accountBalances.put("SB-1001", 45000.0);
        accountBalances.put("SB-1002", 12000.0);
        accountBalances.put("SB-1003", 98000.0);
        accountBalances.merge("SB-1001", 500.0, Double::sum); // update existing safely

        for (Map.Entry<String, Double> entry : accountBalances.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // TreeMap keeps keys sorted automatically
        Map<String, Double> sortedBalances = new TreeMap<>(accountBalances);
        System.out.println("Sorted by account number: " + sortedBalances);

        // ---- Sorting a List with a custom Comparator (generics + lambdas) ----
        List<String> byBalanceDescending = new ArrayList<>(accountBalances.keySet());
        byBalanceDescending.sort(
                Comparator.comparingDouble(accountBalances::get).reversed());
        System.out.println("Accounts sorted by balance (desc): " + byBalanceDescending);

        // ---- Our own generic Pair<A, B> in use ----
        Pair<String, Double> topAccount = new Pair<>(byBalanceDescending.get(0),
                accountBalances.get(byBalanceDescending.get(0)));
        System.out.println("Top account pair: " + topAccount);

        // ---- Bounded generic method: works for any List of Number subtypes ----
        List<Double> depositAmounts = List.of(1200.5, 300.0, 4500.75);
        System.out.println("Sum of deposits: " + sumOf(depositAmounts));
    }

    // Bounded type parameter: T must be a Number or a subclass of Number.
    static <T extends Number> double sumOf(List<T> numbers) {
        double total = 0;
        for (T number : numbers) {
            total += number.doubleValue();
        }
        return total;
    }
}
