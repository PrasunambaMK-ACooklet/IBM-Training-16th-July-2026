package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo039_streamApI {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Prasunamba");
        list.add("Rama");
        list.add("Seeta");
        List<String> filtered = list.stream()
                .filter(name -> name.startsWith("S"))
                .collect(Collectors.toList());
        System.out.println(filtered);
    }
}
