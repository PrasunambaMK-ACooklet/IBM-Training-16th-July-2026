import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TOPIC: Test-Driven Development (TDD) Overview
 *
 * TDD follows a short, repeating cycle for every new bit of behaviour:
 *
 *   RED      -> write a test for behaviour that doesn't exist yet. It MUST
 *               fail (often it won't even compile), proving the test is
 *               actually testing something real.
 *   GREEN    -> write the SMALLEST amount of production code needed to
 *               make that test pass. Resist the urge to build more than
 *               the test currently demands.
 *   REFACTOR -> now that the test is green (a safety net), clean up the
 *               code - remove duplication, improve names - re-running the
 *               tests after every change to make sure they stay green.
 *
 * The tests below are written in the ORDER they would have been in a real
 * TDD session for LateFeeCalculator, each one annotated with which stage of
 * the cycle it represents. LateFeeCalculator.java is the class that
 * resulted from following this process to the end.
 */
class TddLateFeeCalculatorTest {

    // ---- CYCLE 1 ----
    // RED: LateFeeCalculator didn't exist at all before this test was
    // written. Writing it first forces us to decide the API shape
    // (a calculate(int) method returning a double) before any implementation exists.
    // GREEN: the simplest implementation that could possibly work is
    // "return 0.0 always" - which is genuinely enough to pass just this one test.
    @Test
    @DisplayName("Cycle 1 (RED -> GREEN): zero days late means zero fee")
    void zeroDaysLateMeansZeroFee() {
        LateFeeCalculator calculator = new LateFeeCalculator();
        assertEquals(0.0, calculator.calculate(0), 0.001);
    }

    // ---- CYCLE 2 ----
    // RED: this test FAILS against the "always return 0.0" implementation
    // from cycle 1, forcing real logic to be added.
    // GREEN: now daysLate * 50.0 is required to make both tests pass together.
    @Test
    @DisplayName("Cycle 2 (RED -> GREEN): each day late adds a flat fee")
    void eachDayLateAddsFlatFee() {
        LateFeeCalculator calculator = new LateFeeCalculator();
        assertEquals(150.0, calculator.calculate(3), 0.001);
    }

    // ---- CYCLE 3 ----
    // RED: exposes a missing rule - fees shouldn't grow forever.
    // GREEN: introduces the Math.min(fee, MAX_LATE_FEE) cap.
    @Test
    @DisplayName("Cycle 3 (RED -> GREEN): the fee is capped at a maximum")
    void feeIsCappedAtMaximum() {
        LateFeeCalculator calculator = new LateFeeCalculator();
        assertEquals(500.0, calculator.calculate(30), 0.001); // 30*50=1500, capped to 500
    }

    // ---- CYCLE 4 ----
    // RED: a negative "days late" is nonsensical and wasn't considered yet.
    // GREEN: adds the `daysLate <= 0` guard clause.
    @Test
    @DisplayName("Cycle 4 (RED -> GREEN): a negative days-late value is treated as zero fee")
    void negativeDaysLateMeansZeroFee() {
        LateFeeCalculator calculator = new LateFeeCalculator();
        assertEquals(0.0, calculator.calculate(-5), 0.001);
    }

    // REFACTOR note: after each GREEN step above, LateFeeCalculator.java was
    // revisited to keep the implementation minimal and clearly named
    // (DAILY_LATE_FEE, MAX_LATE_FEE constants) WITHOUT changing behaviour -
    // re-running this whole test class after each tweak is what makes that
    // safe. That's the complete Red-Green-Refactor loop, four times over.
}
