package com.bank.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * TOPIC: Java Annotations
 *
 * Annotations attach metadata to code without changing its behaviour
 * directly. Covers built-in annotations (@Override, @Deprecated,
 * @FunctionalInterface, @SuppressWarnings) and a custom annotation
 * (@Auditable) read back at runtime via reflection.
 */
public class AnnotationsDemo {

    // Custom annotation definition.
    // @Retention(RUNTIME) -> kept available for reflection at runtime
    // @Target(METHOD)     -> can only be placed on methods
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Auditable {
        String action();
        String riskLevel() default "LOW";
    }

    static class TransactionService {

        @Auditable(action = "WITHDRAW_FUNDS", riskLevel = "HIGH")
        void withdraw(double amount) {
            System.out.println("Withdrawing " + amount);
        }

        @Auditable(action = "VIEW_BALANCE")
        void viewBalance() {
            System.out.println("Viewing balance");
        }

        void internalHelperNotAudited() {
            System.out.println("Not an audited operation");
        }
    }

    static class LegacyReportGenerator {
        @Deprecated // built-in: warns callers this method should no longer be used
        void generateReportOldWay() {
            System.out.println("Generating report the old (deprecated) way");
        }

        void generateReportNewWay() {
            System.out.println("Generating report the new way");
        }
    }

    static class Base {
        void audit() {
            System.out.println("Base audit");
        }
    }

    static class Derived extends Base {
        @Override // built-in: compiler verifies this really overrides a parent method
        void audit() {
            System.out.println("Derived audit");
        }
    }

    @SuppressWarnings("unchecked") // built-in: silences a specific compiler warning
    static void rawTypeExampleSuppressed() {
        java.util.List list = new java.util.ArrayList(); // raw type, normally warns
        list.add("suppressed warning demo");
        System.out.println(list);
    }

    public static void main(String[] args) throws Exception {
        new LegacyReportGenerator().generateReportNewWay();
        new Derived().audit();
        rawTypeExampleSuppressed();

        // Reading our custom @Auditable annotation back at runtime via reflection.
        System.out.println("\nScanning TransactionService for @Auditable methods:");
        for (Method method : TransactionService.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Auditable.class)) {
                Auditable auditInfo = method.getAnnotation(Auditable.class);
                System.out.println(" -> " + method.getName() +
                        " | action=" + auditInfo.action() + " | risk=" + auditInfo.riskLevel());
            }
        }
    }
}
