package com.bank.events;

import com.bank.model.TransactionType;

import java.util.ArrayList;
import java.util.List;

/**
 * TOPIC: Event Management Usecase
 *
 * TransactionEventManager is the "subject"/"publisher" in the Observer
 * pattern: it keeps a list of listeners and notifies every one of them
 * whenever publish() is called. This decouples the code that performs a
 * transaction from the code that reacts to it (SMS alerts, email receipts,
 * fraud checks, audit logging, etc. can all be plugged in independently).
 */
class TransactionEventManager {

    private final List<TransactionListener> listeners = new ArrayList<>();

    public void subscribe(TransactionListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(TransactionListener listener) {
        listeners.remove(listener);
    }

    public void publish(TransactionEvent event) {
        for (TransactionListener listener : listeners) {
            listener.onTransaction(event);
        }
    }
}

/**
 * Runnable demo showing multiple independent listeners reacting to the same
 * event: an SMS-style alert and an audit-log style listener.
 */
public class EventManagerDemo {

    public static void main(String[] args) {
        TransactionEventManager manager = new TransactionEventManager();

        // Listener 1: implemented with a lambda (SMS alert simulation)
        TransactionListener smsAlertListener = event ->
                System.out.println("SMS ALERT -> Dear customer, a " + event.getType() +
                        " of " + event.getAmount() + " was made on account " +
                        event.getAccountNumber());

        // Listener 2: implemented with an anonymous inner class (audit log)
        TransactionListener auditLogListener = new TransactionListener() {
            @Override
            public void onTransaction(TransactionEvent event) {
                System.out.println("AUDIT LOG -> " + event);
            }
        };

        manager.subscribe(smsAlertListener);
        manager.subscribe(auditLogListener);

        // Simulate a deposit transaction firing an event
        TransactionEvent depositEvent =
                new TransactionEvent("SB-1001", TransactionType.DEPOSIT, 5000.0, 15000.0);
        manager.publish(depositEvent);

        // Unsubscribe the SMS listener and fire another event
        manager.unsubscribe(smsAlertListener);
        TransactionEvent withdrawalEvent =
                new TransactionEvent("SB-1001", TransactionType.WITHDRAWAL, 2000.0, 13000.0);
        manager.publish(withdrawalEvent);
    }
}
