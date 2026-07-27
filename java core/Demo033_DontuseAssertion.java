package org.example;
import java.util.Scanner;
public class Demo033_DontuseAssertion {
    public static void main( String args[] )    {
        Scanner scanner = new Scanner( System.in );
        System.out.print("Enter the ID ");
        int value = scanner.nextInt();
        assert value>=15:" Invalid";
        System.out.println("Value "+value);
    }
}