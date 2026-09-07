package com.bank.jvm;

/**
 * TOPIC: JVM Architecture
 *
 * The JVM is what makes "write once, run anywhere" possible. Broad picture:
 *
 *  1. CLASS LOADER SUBSYSTEM
 *     - Loading: reads .class bytecode (Bootstrap -> Extension/Platform ->
 *       Application class loaders, in a parent-delegation chain).
 *     - Linking: Verify (bytecode is safe/well-formed) -> Prepare (default
 *       values for static fields) -> Resolve (symbolic references -> direct).
 *     - Initialization: runs static initializers/blocks (see StaticMembersDemo).
 *
 *  2. RUNTIME DATA AREAS (memory)
 *     - Method Area: class-level data - static fields, constant pool, bytecode.
 *     - Heap: where every object (like our Account/Transaction objects) lives;
 *       this is what the Garbage Collector manages (see GarbageCollectionDemo).
 *     - Java Stacks: one per thread; holds stack frames for each method call
 *       (local variables, operand stack, return address).
 *     - PC (Program Counter) Registers: one per thread, tracks the currently
 *       executing instruction.
 *     - Native Method Stacks: support calls into native (C/C++) code, e.g. JNI.
 *
 *  3. EXECUTION ENGINE
 *     - Interpreter: executes bytecode line by line (slower start-up cost).
 *     - JIT (Just-In-Time) Compiler: compiles "hot" bytecode paths to native
 *       machine code at runtime for speed.
 *     - Garbage Collector: reclaims heap memory for unreachable objects.
 *
 *  4. NATIVE METHOD INTERFACE (JNI) & NATIVE METHOD LIBRARIES
 *     - Bridges Java code to platform-specific native libraries when needed.
 *
 * This class doesn't need to "do" anything special to prove the JVM exists -
 * every Java program already runs inside it - so main() below simply prints
 * a few real, queryable facts about the currently running JVM.
 */
public class JvmArchitectureOverview {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("JVM name: " + System.getProperty("java.vm.name"));
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Available processors seen by JVM: " + runtime.availableProcessors());
        System.out.println("Max heap memory JVM may use (bytes): " + runtime.maxMemory());
        System.out.println("Total heap memory currently allocated (bytes): " + runtime.totalMemory());
        System.out.println("Free heap memory right now (bytes): " + runtime.freeMemory());

        System.out.println();
        System.out.println("Allocating some Account-like objects on the HEAP...");
        Object[] simulatedAccounts = new Object[100_000];
        for (int i = 0; i < simulatedAccounts.length; i++) {
            simulatedAccounts[i] = new Object();
        }
        System.out.println("Free heap memory after allocation (bytes): " + runtime.freeMemory());

        simulatedAccounts = null; // drop the only reference - now eligible for GC
        System.gc(); // just a hint to the Garbage Collector inside the Execution Engine
        System.out.println("Requested GC (hint only, not a guarantee it runs immediately).");
    }
}
