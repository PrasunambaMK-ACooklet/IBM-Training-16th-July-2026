package Junit;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Demo05_NestedTests_OrganizedScenarios {
    @Nested
    class DepositTests {
        @Test
        void testDepositPositiveAmountPass() {
            Demo05_BankAccount account = new Demo05_BankAccount("123", 500.0);
            account.deposit(200.0);
            assertEquals(700.0, account.getBalance()); // ✅ passes
        }

        @Test
        void testDepositFail() {
            Demo05_BankAccount account = new Demo05_BankAccount("123", 500.0);
            account.deposit(200.0);
            //fails
            assertEquals(800.0, account.getBalance(), "Expected 800 but got " + account.getBalance());
        }
    }

    @Nested
    class WithdrawTests {
        @Test
        void testWithdrawValidAmountPass() {
            Demo05_BankAccount account = new Demo05_BankAccount("123", 500.0);
            account.withdraw(100.0);
            assertEquals(400.0, account.getBalance()); // ✅ passes
        }

        @Test
        void testWithdrawFail() {
            Demo05_BankAccount account = new Demo05_BankAccount("123", 500.0);
            account.withdraw(100.0);
            //fails
            assertEquals(300.0, account.getBalance(), "Expected 300 but got " + account.getBalance());
        }
    }
}
