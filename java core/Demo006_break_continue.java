package org.example;
class Brk_Cont{
    void breakStatement(){
        for (int i = 0; i <= 10; i++) {
            if (i == 5) break;
            System.out.println(i);
        }
    }
    void continueStatement(){
        for (int i = 0; i <= 10; i++) {
            if (i == 5) continue;
            System.out.println(i);
        }
    }
}
public class Demo006_break_continue {
    public static void main(String[] args) {
        Brk_Cont obj = new Brk_Cont();
        obj.breakStatement();
        obj.continueStatement();
    }
}