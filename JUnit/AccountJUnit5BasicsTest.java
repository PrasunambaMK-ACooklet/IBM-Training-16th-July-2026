import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TOPIC: JUnit 5 Basics (Annotations, Assertions, Lifecycle)
 *
 * Requires (Maven):
 *   <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter</artifactId>
 *     <version>5.10.2</version>
 *     <scope>test</scope>
 *   </dependency>
 */
@DisplayName("SavingsAccount - JUnit 5 basics")
class AccountJUnit5BasicsTest {

    private SavingsAccount account;

    // Runs ONCE before any test method in this class - good for expensive,
    // shared, read-only setup (e.g. starting a container - see the
    // Testcontainers demo for a real example).
    @BeforeAll
    static void setUpOnce() {
        System.out.println("[@BeforeAll] Running once before all tests in this class.");
    }

    // Runs before EVERY single @Test method - each test gets a completely
    // fresh account, so tests cannot accidentally affect one another.
    @BeforeEach
    void setUp() {
        account = new SavingsAccount("SB-2001", new Customer("C-01", "Test Customer"), 5000.0);
    }

    @AfterEach
    void tearDown() {
        System.out.println("[@AfterEach] Finished a test.");
    }

    @AfterAll
    static void tearDownOnce() {
        System.out.println("[@AfterAll] Running once after all tests in this class.");
    }

    @Test
    @DisplayName("depositing a positive amount increases the balance")
    void depositIncreasesBalance() {
        account.deposit(1500.0);

        // assertEquals(expected, actual, [message])
        assertEquals(6500.0, account.getBalance(), 0.001, "balance should reflect the deposit");
    }

    @Test
    void newAccountIsNotNullAndHasOwner() {
        assertNotNull(account);
        assertNotNull(account.getOwner());
        assertEquals("C-01", account.getOwner().getCustomerId());
    }

    @Test
    @DisplayName("withdrawing more than the balance throws InsufficientFundsException")
    void withdrawingTooMuchThrows() {
        // assertThrows returns the exception so you can assert on its message too.
        InsufficientFundsException exception = assertThrows(
                InsufficientFundsException.class,
                () -> account.withdraw(999999.0));

        assertTrue(exception.getMessage().contains("Insufficient funds"));
    }

    @Test
    void depositOfNegativeAmountThrowsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100.0));
    }

    @Test
    void withdrawingWithinMinimumBalanceSucceeds() throws InsufficientFundsException {
        account.withdraw(1000.0); // leaves exactly 4000, well above the 1000 minimum
        assertEquals(4000.0, account.getBalance(), 0.001);
        assertFalse(account.getBalance() < SavingsAccount.MIN_BALANCE);
    }

    @Test
    @Disabled("Example of temporarily skipping a test, e.g. while a feature is mid-development")
    void thisTestIsSkipped() {
        throw new RuntimeException("This body never runs because of @Disabled");
    }
}
