package Junit;

public class Demo06_PerformanceService {
    public void quickOperation() throws InterruptedException {
        Thread.sleep(100); // fast operation
    }

    public void slowOperation() throws InterruptedException {
        Thread.sleep(1000); // deliberately slow operation
    }
}
