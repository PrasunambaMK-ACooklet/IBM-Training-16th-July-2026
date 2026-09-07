package com.bank.model;

/**
 * TOPIC: Encapsulation
 *
 * All fields are private and only reachable through public getters/setters.
 * The outside world cannot directly poke at a Customer's internal state,
 * which protects the object's invariants (e.g. an empty name is rejected).
 */
public class Customer {

    private final String customerId;
    private String fullName;
    private String email;

    public Customer(String customerId, String fullName, String email) {
        this.customerId = customerId;
        setFullName(fullName);
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{id='" + customerId + "', name='" + fullName + "'}";
    }
}
