/**
 * TOPIC: Introduction to Unit Testing Concepts
 *
 * Before reaching for a framework like JUnit, it helps to see what a "unit
 * test" fundamentally IS: a small, automated piece of code that
 *   1. sets up some input (ARRANGE),
 *   2. calls the exact piece of code being tested (ACT),
 *   3. checks the actual result against the expected result (ASSERT),
 * and reports pass/fail WITHOUT any human watching console output.
 *
 * This class hand-rolls that idea with plain "if" statements and no
 * framework at all, specifically to make the AAA (Arrange-Act-Assert)
 * pattern obvious before AccountJUnit5BasicsTest.java replaces this
 * home-grown machinery with real JUnit 5 annotations and assertions.
 *
 * Good unit tests are also:
 *   - FAST        (milliseconds, so you can run thousands of them constantly)
 *   - INDEPENDENT (each test can run alone, in any order, and still pass)
 *   - REPEATABLE  (same result every time - no reliance on "today's date",
 *                  network calls, or shared mutable state - see TestableCodeDemo)
 *   - SELF-CHECKING (pass/fail is automatic, not "read the printed number
 *                  yourself and decide if it looks right")
 */
public class IntroToUnitTestingConceptsDemo {

    private static int testsRun = 0;
    private static int testsPassed = 0;

    public static void main(String[] args) {
        runTest("deposit increases balance", IntroToUnitTestingConceptsDemo::depositIncreasesBalance);
        runTest("withdraw below minimum balance is rejected", IntroToUnitTestingConceptsDemo::withdrawBelowMinimumIsRejected);
        runTest("withdraw more than balance throws", IntroToUnitTestingConceptsDemo::withdrawMoreThanBalanceThrows);

        System.out.println();
        System.out.println(testsPassed + " / " + testsRun + " tests passed.");
    }

    // ---- ARRANGE / ACT / ASSERT, written out by hand ----
    static void depositIncreasesBalance() throws Exception {
        // Arrange
        Account account = new SavingsAccount("SB-1001", new Customer("C1", "Test User"), 1000.0);
        // Act
        account.deposit(500.0);
        // Assert
        assertEquals(1500.0, account.getBalance(), "balance after deposit");
    }

    static void withdrawBelowMinimumIsRejected() {
        Account account = new SavingsAccount("SB-1002", new Customer("C2", "Test User"), 1200.0);
        boolean threw = false;
        try {
            account.withdraw(300.0); // would leave 900, below the 1000 minimum
        } catch (InsufficientFundsException e) {
            threw = true;
        }
        assertTrue(threw, "withdrawing below minimum balance should throw");
    }

    static void withdrawMoreThanBalanceThrows() {
        Account account = new SavingsAccount("SB-1003", new Customer("C3", "Test User"), 1000.0);
        boolean threw = false;
        try {
            account.withdraw(999999.0);
        } catch (InsufficientFundsException e) {
            threw = true;
        }
        assertTrue(threw, "withdrawing more than the balance should throw");
    }

    // ---- tiny hand-rolled assertion + runner helpers ----
    interface TestCase {
        void run() throws Exception;
    }

    static void runTest(String name, TestCase testCase) {
        testsRun++;
        try {
            testCase.run();
            testsPassed++;
            System.out.println("PASS - " + name);
        } catch (AssertionError | Exception e) {
            System.out.println("FAIL - " + name + " -> " + e.getMessage());
        }
    }

    static void assertEquals(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.0001) {
            throw new AssertionError(message + ": expected " + expected + " but was " + actual);
        }
    }

    static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
