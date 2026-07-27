package org.example;
class Employee{
    int details() {
        int emp_id = 1001;
        System.out.println(emp_id);
        return emp_id;
    }
}
class Manager extends Employee {
    int amount = 500;
    void accept() {
        System.out.println("accept called");
        int amt = this.amount;
        int amt1 = amount;
        System.out.println(amt1);
        System.out.println(amt);
        int id = this.details();
        System.out.println(id);
    }
    int display() {
        accept();
        int e_id = super.details();
        System.out.println(e_id);
        return e_id;
    }
}
public class Demo015_SingleInheritance {
    public static void main(String[] args) {
        // TODO: code here
        Manager mobj = new Manager();
        mobj.display();
    }
}