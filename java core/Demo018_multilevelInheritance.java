package org.example;
class Person03 {
    int id = 101;
    void display() {
        System.out.println("I m a person");
    }
}
class Employee03 extends Person03{
    void display() {
        Person03 pobj = new Person03();
        pobj.display();
        System.out.println("I m an employee ");
    }
}
class Manager03 extends Employee03 {
    void display() {
        Employee03 eobj = new Employee03();
        eobj.display();
        Person03 pobj = new Person03();
        int id = pobj.id;
        System.out.println(id + " also "+ pobj.id);
        System.out.println("I m a manager");
    }
}
public class Demo018_multilevelInheritance {
    public static void main(String[] args) {
        // TODO: code here
        Manager03 mobj = new Manager03();
        mobj.display();
    }
}