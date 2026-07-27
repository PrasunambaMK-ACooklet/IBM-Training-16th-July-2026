package org.example;
import java.util.ArrayDeque;
import java.util.Deque;
public class Demo024_Stack {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.pop();
        System.out.println(stack);
    }
}