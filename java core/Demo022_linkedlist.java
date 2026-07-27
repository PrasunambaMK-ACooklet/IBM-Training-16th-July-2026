package org.example;
import java.util.LinkedList;
public class Demo022_linkedlist {
    public static void main(String[] args) {
        LinkedList<String> Names = new LinkedList<>();
        Names.add("Riya");
        Names.add("Priya");
        Names.add("Seeta");
        Names.add("Rama");
        Names.add("Krishna");
        System.out.println(Names);
        Names.remove(1);
        System.out.println(Names);
        Names.addFirst("Geeta");
        System.out.println(Names);
    }
}