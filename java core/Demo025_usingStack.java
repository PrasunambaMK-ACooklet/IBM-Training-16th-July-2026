package org.example;
import java.util.Stack;

public class Demo025_usingStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("Card A");
        stack.push("Card B");
        stack.push("Card C");

        System.out.println("Top element: " + stack.peek());

        System.out.println("Removed: " + stack.pop());

        System.out.println("Is stack empty? " + stack.empty());
    }
}