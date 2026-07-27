package org.example;
class Customer01 {
    int details() {
        int cust_id = 101;
        System.out.println("printing cust id " + cust_id);
        return cust_id;
    }
}
class Employee01 {
    int details() {
        int emp_id = 102;
        System.out.println("printing emp id " + emp_id);
        return emp_id;
    }
}
class Manager01 extends Employee01 {
    int details() {
        int mgr_id = 103;
        System.out.println("printing mgr id " + mgr_id);
        return mgr_id;
    }
}

public class Demo017_MultipleInheritance {
    public static void main(String[] args) {
        // TODO: code here
        Customer01 cobj = new Customer01();
        cobj.details(); // 101
        Manager01 mobj = new Manager01();
        mobj.details(); // 103
        Employee01 eobj = new Employee01();
        eobj.details(); // 102
        Employee01 eobj1 = new Manager01();
        eobj1.details(); // 103
    }
}