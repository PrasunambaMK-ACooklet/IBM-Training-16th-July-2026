package org.example;
import java.util.LinkedList;
import java.util.Queue;
public class Demo026_Queue {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        q.add("hello");
        q.add("how ");
        q.add("r u");
        System.out.println(q.peek());
    }
}