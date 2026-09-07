import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TOPIC: Code Coverage Concepts
 *
 * Code coverage measures how much of your PRODUCTION code your tests
 * actually execute. Common metrics (e.g. reported by JaCoCo in Maven/Gradle):
 *   - LINE coverage     : % of executable lines run by at least one test.
 *   - BRANCH coverage    : % of if/else, switch, ternary, && / || outcomes run.
 *   - METHOD/CLASS cover.: % of methods/classes touched at all.
 *
 * IMPORTANT LESSON: coverage is a USEFUL SIGNAL, not a GOAL in itself.
 * "100% coverage" only means every line/branch ran at least once - it says
 * NOTHING about whether the test actually asserted the right thing. A test
 * with no assertions can give 100% line coverage while proving nothing.
 *
 * This test class is deliberately INCOMPLETE on purpose: it only exercises
 * SOME of LoanEligibilityChecker's branches, so that running a coverage
 * tool (e.g. `mvn test jacoco:report`) over just this class visibly shows
 * gaps - specifically the REJECTED_EXISTING_LOAN_LOW_INCOME and
 * APPROVED_WITH_CONDITIONS branches are never reached below.
 * CodeCoverageFullDemoTest.java (further down this file) shows what closing
 * those gaps looks like.
 */
class CodeCoverageConceptsDemoTest {

    private final LoanEligibilityChecker checker = new LoanEligibilityChecker();

    @Test
    @DisplayName("high credit score and high income -> premium approval (covers one branch only)")
    void highIncomeHighScoreIsApprovedPremium() {
        assertEquals("APPROVED_PREMIUM", checker.checkEligibility(750, 150000.0, false));
    }

    @Test
    @DisplayName("low credit score -> rejected regardless of income (covers another branch)")
    void lowCreditScoreIsRejected() {
        assertEquals("REJECTED_LOW_CREDIT_SCORE", checker.checkEligibility(500, 500000.0, false));
    }

    // NOTE: no test here touches hasExistingLoan == true at all - a coverage
    // report would flag that entire inner if/else block as uncovered. See
    // the companion class below for the fix.
}

/**
 * The SAME method, now with every branch covered - compare its shape to
 * LoanEligibilityChecker.checkEligibility() to see each test line up with
 * exactly one decision path through the code.
 */
class CodeCoverageFullDemoTest {

    private final LoanEligibilityChecker checker = new LoanEligibilityChecker();

    @Test
    void lowCreditScoreIsRejected() {
        assertEquals("REJECTED_LOW_CREDIT_SCORE", checker.checkEligibility(500, 500000.0, false));
    }

    @Test
    void existingLoanWithLowIncomeIsRejected() {
        assertEquals("REJECTED_EXISTING_LOAN_LOW_INCOME", checker.checkEligibility(700, 30000.0, true));
    }

    @Test
    void existingLoanWithSufficientIncomeIsApprovedWithConditions() {
        assertEquals("APPROVED_WITH_CONDITIONS", checker.checkEligibility(700, 60000.0, true));
    }

    @Test
    void noExistingLoanHighIncomeIsApprovedPremium() {
        assertEquals("APPROVED_PREMIUM", checker.checkEligibility(700, 150000.0, false));
    }

    @Test
    void noExistingLoanModerateIncomeIsApprovedStandard() {
        assertEquals("APPROVED_STANDARD", checker.checkEligibility(700, 50000.0, false));
    }

    @Test
    void noExistingLoanLowIncomeIsRejected() {
        assertEquals("REJECTED_LOW_INCOME", checker.checkEligibility(700, 10000.0, false));
    }
}
