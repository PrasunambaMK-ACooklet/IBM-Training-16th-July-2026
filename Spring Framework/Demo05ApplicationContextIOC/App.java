package com.SpringFw.App;
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
//here we r manually creating a container
// (AnnotationConfigApplicationContext)
// juz to know internal work of spring
// actually Spring Boot will creates automatically
// using SpringApplication.run(). so commented it
    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Employee employee = context.getBean(Employee.class);

        System.out.println(employee);
    }
}