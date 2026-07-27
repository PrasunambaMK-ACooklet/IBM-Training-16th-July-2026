package com.SpringFw;

public class Customer {
    private String name;

    // Setter for Spring injection
    public void setName(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "'}";
    }
}
