/**
 * TOPIC (supporting class for): Test-Driven Development (TDD) Overview
 *
 * This class is the END RESULT of the Red-Green-Refactor walkthrough shown
 * step by step, as comments, inside TddLateFeeCalculatorTest.java. In real
 * TDD you would NOT write this file first - you'd let the failing tests
 * drive you to write exactly this much code and no more.
 */
public class LateFeeCalculator {

    private static final double DAILY_LATE_FEE = 50.0;
    private static final double MAX_LATE_FEE = 500.0;

    public double calculate(int daysLate) {
        if (daysLate <= 0) {
            return 0.0;
        }
        double fee = daysLate * DAILY_LATE_FEE;
        return Math.min(fee, MAX_LATE_FEE);
    }
}
