import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TOPIC: Parameterized and Nested Tests
 *
 * Requires (Maven, in addition to junit-jupiter):
 *   <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter-params</artifactId>
 *     <version>5.10.2</version>
 *     <scope>test</scope>
 *   </dependency>
 */
class AccountParameterizedNestedTest {

    // ---- PARAMETERIZED TESTS: run the same test logic against many inputs ----

    @ParameterizedTest(name = "depositing {0} should be rejected as invalid")
    @ValueSource(doubles = {0.0, -1.0, -500.0})
    @DisplayName("Non-positive deposit amounts are always rejected")
    void nonPositiveDepositsAreRejected(double invalidAmount) {
        SavingsAccount account = new SavingsAccount("SB-3001", new Customer("C-02", "Param User"), 1000.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(invalidAmount));
    }

    @ParameterizedTest(name = "opening={0}, withdraw={1} -> expectedBalance={2}")
    @CsvSource({
            "5000.0, 1000.0, 4000.0",
            "5000.0, 4000.0, 1000.0",
            "2000.0, 500.0,  1500.0"
    })
    void withdrawalsThatStayAboveMinimumSucceed(double opening, double withdrawal, double expectedBalance)
            throws InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("SB-3002", new Customer("C-03", "Param User"), opening);
        account.withdraw(withdrawal);
        assertEquals(expectedBalance, account.getBalance(), 0.001);
    }

    // A @MethodSource lets you supply richer, programmatically-built test data.
    static Stream<org.junit.jupiter.params.provider.Arguments> loanEligibilityCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(750, 120000.0, false, "APPROVED_PREMIUM"),
                org.junit.jupiter.params.provider.Arguments.of(750, 40000.0, false, "APPROVED_STANDARD"),
                org.junit.jupiter.params.provider.Arguments.of(750, 10000.0, false, "REJECTED_LOW_INCOME"),
                org.junit.jupiter.params.provider.Arguments.of(550, 200000.0, false, "REJECTED_LOW_CREDIT_SCORE")
        );
    }

    @ParameterizedTest
    @MethodSource("loanEligibilityCases")
    void loanEligibilityMatrix(int score, double income, boolean hasExistingLoan, String expected) {
        LoanEligibilityChecker checker = new LoanEligibilityChecker();
        assertEquals(expected, checker.checkEligibility(score, income, hasExistingLoan));
    }

    // ---- NESTED TESTS: group related scenarios under a shared, named context ----

    @Nested
    @DisplayName("When the account has a healthy balance")
    class WithHealthyBalance {

        SavingsAccount account;

        @BeforeEach
        void setUp() {
            account = new SavingsAccount("SB-3003", new Customer("C-04", "Nested User"), 10000.0);
        }

        @Test
        @DisplayName("a normal withdrawal succeeds")
        void normalWithdrawalSucceeds() throws InsufficientFundsException {
            account.withdraw(2000.0);
            assertEquals(8000.0, account.getBalance(), 0.001);
        }

        @Nested
        @DisplayName("and a large deposit is made")
        class AndLargeDepositIsMade {

            @BeforeEach
            void depositLargeAmount() {
                account.deposit(50000.0); // runs AFTER the outer @BeforeEach
            }

            @Test
            @DisplayName("the balance reflects both the opening amount and the deposit")
            void balanceReflectsBothAmounts() {
                assertEquals(60000.0, account.getBalance(), 0.001);
            }
        }
    }

    @Nested
    @DisplayName("When the account is right at the minimum balance")
    class AtMinimumBalance {

        SavingsAccount account;

        @BeforeEach
        void setUp() {
            account = new SavingsAccount("SB-3004", new Customer("C-05", "Nested User"), SavingsAccount.MIN_BALANCE);
        }

        @Test
        @DisplayName("any withdrawal is rejected")
        void anyWithdrawalIsRejected() {
            assertThrows(InsufficientFundsException.class, () -> account.withdraw(1.0));
        }
    }
}
