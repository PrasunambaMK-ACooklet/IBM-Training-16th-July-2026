package com.SpringFw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class AppConfig {

    @Bean
    public Customer customer(@Value("Prasunamba") String customerName) {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        return customer;
    }
}