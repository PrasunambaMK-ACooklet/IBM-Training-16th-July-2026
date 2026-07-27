    package org.example;
    interface Math {
        int compute(int a, int b);
    }
    public class Demo034_LambdaExp {
        public static void main(String[] args) {
            Math multiply = (a, b) -> a * b;
            int result = multiply.compute(10, 20);
            System.out.println("prod is : " + result);
        }
    }