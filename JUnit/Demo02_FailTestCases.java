package Junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Demo02_BankAccountTest {
    @Test
    void testDepositFail() {
        Demo02_BankAccount account = new Demo02_BankAccount("123", 500.0);
        account.deposit(200.0);
        //fails
        assertEquals(800.0, account.getBalance(), "Expected 800 but got " + account.getBalance());
    }

    @Test
    void testWithdrawFail() {
        Demo02_BankAccount account = new Demo02_BankAccount("123", 500.0);
        account.withdraw(200.0);
        //fails
        assertEquals(400.0, account.getBalance(), "Expected 400 but got " + account.getBalance());
    }

    @Test
    void testExceptionFail() {
        Demo02_BankAccount account = new Demo02_BankAccount("123", 100.0);
        //fails
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(50));
    }
}
