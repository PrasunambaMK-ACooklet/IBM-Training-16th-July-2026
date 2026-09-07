package com.bank.strings;

/**
 * TOPIC: String Handling
 *
 * Covers String immutability, the string pool, StringBuilder for efficient
 * concatenation, and common String API methods used in everyday code.
 */
public class StringHandlingDemo {

    public static void main(String[] args) {
        // ---- immutability & the string constant pool ----
        String accountHolder1 = "Ramesh Kumar"; // placed in the string pool
        String accountHolder2 = "Ramesh Kumar"; // reuses the SAME pooled object
        String accountHolder3 = new String("Ramesh Kumar"); // forces a NEW object on the heap

        System.out.println("Pooled literals ==: " + (accountHolder1 == accountHolder2));       // true
        System.out.println("new String() ==: " + (accountHolder1 == accountHolder3));           // false
        System.out.println("Content equality with .equals(): " + accountHolder1.equals(accountHolder3)); // true

        // Every "modification" actually creates a brand-new String object;
        // the original is never changed - that's what "immutable" means.
        String name = "ramesh";
        String upperName = name.toUpperCase();
        System.out.println("Original unchanged: " + name + ", new object: " + upperName);

        // ---- common String API methods ----
        String ifscCode = "  HDFC0001234  ";
        System.out.println("Trimmed: '" + ifscCode.trim() + "'");
        System.out.println("Length: " + ifscCode.trim().length());
        System.out.println("Substring (bank code): " + ifscCode.trim().substring(0, 4));
        System.out.println("Contains 'HDFC': " + ifscCode.contains("HDFC"));
        System.out.println("Replace: " + ifscCode.trim().replace("HDFC", "SBIN"));
        System.out.println("Split account holder name: " + java.util.Arrays.toString(accountHolder1.split(" ")));
        System.out.println("Formatted statement line: " +
                String.format("A/C %-10s Balance: %10.2f", "SB-100", 45231.5));

        // ---- StringBuilder: mutable, efficient for repeated concatenation ----
        StringBuilder statementBuilder = new StringBuilder();
        statementBuilder.append("Mini Statement\n");
        statementBuilder.append("---------------\n");
        double[] transactions = {1500.0, -200.0, 3000.0, -750.0};
        for (double t : transactions) {
            statementBuilder.append(t > 0 ? "CREDIT: " : "DEBIT: ")
                    .append(Math.abs(t))
                    .append("\n");
        }
        statementBuilder.insert(0, "==== "); // insert at a specific position
        statementBuilder.reverse().reverse(); // just to show reverse() exists
        System.out.println(statementBuilder);

        // Why StringBuilder matters: concatenating Strings in a loop with "+"
        // creates a brand new String object on every single iteration, which
        // is wasteful for large loops - StringBuilder mutates one buffer instead.
    }
}
