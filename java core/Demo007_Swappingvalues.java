package org.example;

public class Demo007_Swappingvalues {
    public static void main(String[] args) {
        int x = 10;
        int y = 20;
        int temp;
        System.out.println("x and y values after swapping "+ x + " and "+y);
        temp = x;
        x = y;
        y = temp;
        System.out.println("x and y values after swapping "+ x + " and "+y);
    }
}