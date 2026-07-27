package org.example;
class Loops{
    void forloop() {
        for(int age = 10; age < 20; age++) {
            System.out.println(age);
        }
    }
    void whileloop() {
        int id = 10;
        while (id <= 20) {
            System.out.println(id++);
        }
    }
    void dowhileloop() {
        int id = 10;
        do {
            System.out.println(id++);
        }while(id <= 20);
    }
    void foreachloop() {
        String[] names = {"vena", "tena", "Ram"};
        for (String name : names) {
            System.out.println(name);
        }
    }
}
public class Demo005_loops {
    public static void main(String[] args) {
        Loops lobj = new Loops();
        lobj.forloop();
        lobj.whileloop();
        lobj.dowhileloop();
        lobj.foreachloop();
    }
}