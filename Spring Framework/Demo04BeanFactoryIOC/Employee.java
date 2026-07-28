package com.SpringFw;
// this is my POJO class
public class Employee {
    private String firstName;
    private String lastName;
    private String middleName;

    public Employee() {
        System.out.println("employee default constructor called");
    }

    public Employee(String fName, String mName, String lName) {
        this.firstName = fName;
        this.middleName = mName;
        this.lastName = lName;
    }
    @Override
    public String toString() {
        return "Employee{"+ "Name is "+ firstName +" "+ middleName + " "+lastName;
    }
}