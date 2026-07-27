package com.SpringFw;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Component
public class Customer {
    private String name;

    @Autowired
    public void setCustomerName(@Value("Prasunamba") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "' }";
    }
}