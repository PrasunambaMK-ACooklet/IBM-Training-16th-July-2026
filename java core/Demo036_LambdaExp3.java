package org.example;
@FunctionalInterface
interface  OParameter{
    void display();
}
public class Demo036_LambdaExp3 {
    public static void main(String[] args) {
        OParameter obj = ()
                -> System.out.println(
                "This is a zero-parameter lambda expression!");
        obj.display();
    }
}
