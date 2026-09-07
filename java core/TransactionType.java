package com.bank.model;

/**
 * TOPIC: Enums
 *
 * Represents the kind of banking transaction. Using an enum instead of raw
 * Strings ("DEPOSIT", "WITHDRAW", ...) gives us compile-time safety -
 * typos are caught by the compiler instead of causing runtime bugs.
 */
public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER_IN,
    TRANSFER_OUT,
    INTEREST_CREDIT
}
