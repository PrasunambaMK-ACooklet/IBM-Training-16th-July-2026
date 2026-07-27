package org.example;
class Customer{
    int id = 200;
    int details (int id) {
        this.id = id;
        System.out.println(id);
        System.out.println(this.id);
        return id;
    }
}
public class Demo016_thisKeyword {
    public static void main(String[] args) {
        // TODO: code here
        Customer cobj = new Customer();
        cobj.details(100);
    }
}