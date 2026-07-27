package org.example;

public class Demo002_datatypes {
    public static void main(String[] args) {
        // TODO: code here
        System.out.println("two types of data types we have ");
        System.out.println(" primitive data types int, char float.. ");
        System.out.println("non primitive data types arrays...");
        int age = 10;
        char gender = 'F';
        float amount = 100.50f;
        Demo001_comments obj = new Demo001_comments();
        System.out.println(obj.default_var);
        System.out.println("age is " + age + " gender is " + gender + " amount is " + amount);
    }
}