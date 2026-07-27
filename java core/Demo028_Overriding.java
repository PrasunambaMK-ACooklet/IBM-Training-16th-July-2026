package org.example;
class Parent {
    void display() {
        System.out.println("I m parent");
    }
}
class Child extends Parent {
    void display() {
        System.out.println("I m child");
    }
}
public class Demo028_Overriding {
    public static void main(String[] args) {
        Parent pobj = new Parent();
        pobj.display();
        Child cobj = new Child();
        cobj.display();
    }
}