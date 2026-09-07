package com.bank.basics;

/**
 * TOPIC: Software Installation and First Java Program
 *
 * The traditional first program, adapted to our banking theme. If this
 * prints correctly, your JDK is installed and configured correctly:
 *
 *   1. Install a JDK (e.g. OpenJDK 21 LTS).
 *   2. Confirm with:  java -version   and   javac -version
 *   3. Compile:       javac HelloBank.java
 *   4. Run:           java com.bank.basics.HelloBank   (from the src root)
 *
 * Anatomy of this file:
 *   - "package"      : which folder/namespace this class belongs to.
 *   - "public class" : the class name MUST match the file name (HelloBank.java).
 *   - "public static void main(String[] args)" : the JVM's required entry point.
 *   - "System.out.println" : prints text followed by a newline to the console.
 */
public class HelloBank {
    public static void main(String[] args) {
        System.out.println("Welcome to Acme National Bank's core banking system!");
        System.out.println("Your Java environment is set up correctly.");
    }
}
