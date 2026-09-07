package com.bank.events;

/**
 * TOPIC: Event Management Usecase
 *
 * The "listener" contract in the classic Observer pattern. Any class (or
 * lambda, since this is a @FunctionalInterface) that wants to be notified
 * whenever a transaction happens implements this single method.
 */
@FunctionalInterface
public interface TransactionListener {
    void onTransaction(TransactionEvent event);
}
