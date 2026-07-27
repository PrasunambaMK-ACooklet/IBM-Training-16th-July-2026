package org.example;
class Employee04 {
    private int private_var = 10;
    public int public_var = 20;
    protected int protected_var = 50;
    void details() {
        System.out.println("employee details private var" + private_var + "public var "+ public_var);
        System.out.println("employee details protected var" + protected_var);
    }
}
class Manager04 extends Employee04 {
    int var = public_var = 30;
    int var2 = protected_var = 40;
        void details() {
        System.out.println("manager details public var "+ var);
        System.out.println("manager details protected var "+ var2);
    }
}
class Outsider{
    Employee04 eobj2 = new Employee04();
    int var1 = eobj2.public_var = 40;
    void details() {
        System.out.println("outsider details public var "+ var1);
    }
}
public class Demo019_accessModifiers {
    public static void main(String[] args) {
        // TODO: code here
        Manager04 mobj = new Manager04();
        mobj.details();
        Outsider oobj = new Outsider();
        oobj.details();
    }
}