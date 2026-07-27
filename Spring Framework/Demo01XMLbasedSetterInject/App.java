package com.SpringFw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Customer customer;

    // Setter for Spring injection
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void display() {
        System.out.println("Injected Customer: " + customer);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        App app = (App) context.getBean("app");
        app.display();
    }
}
