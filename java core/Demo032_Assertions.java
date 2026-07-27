package org.example;
import java.util.Scanner;
public class Demo032_Assertions {
    public static void main(String[] args) {
        int age = 17;
        assert age >= 18 : "can't vote";
        System.out.println(" u r " + age);
    }
}