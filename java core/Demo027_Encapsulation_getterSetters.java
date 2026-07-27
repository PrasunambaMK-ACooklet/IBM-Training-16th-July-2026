package org.example;
class Encap {
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
public class Demo027_Encapsulation_getterSetters {
    public static void main(String[] args) {
        Encap obj = new Encap();
        obj.setName("Prasunamba");
        System.out.println(obj.getName());
    }
}