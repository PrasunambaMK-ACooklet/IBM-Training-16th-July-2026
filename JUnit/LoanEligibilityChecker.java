/**
 * TOPIC (supporting class for): Code Coverage Concepts
 *
 * Deliberately written with several independent branches so that
 * CodeCoverageConceptsDemoTest can show the difference between:
 *   - LINE coverage   (was this line executed at all?)
 *   - BRANCH coverage (was EVERY if/else path executed, not just one side?)
 * A high line-coverage % can still hide completely untested branches.
 */
public class LoanEligibilityChecker {

    public String checkEligibility(int creditScore, double monthlyIncome, boolean hasExistingLoan) {
        if (creditScore < 600) {
            return "REJECTED_LOW_CREDIT_SCORE";
        }

        if (hasExistingLoan) {
            if (monthlyIncome < 50000) {
                return "REJECTED_EXISTING_LOAN_LOW_INCOME";
            } else {
                return "APPROVED_WITH_CONDITIONS";
            }
        }

        if (monthlyIncome >= 100000) {
            return "APPROVED_PREMIUM";
        } else if (monthlyIncome >= 30000) {
            return "APPROVED_STANDARD";
        }

        return "REJECTED_LOW_INCOME";
    }
}
