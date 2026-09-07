import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * TOPIC: Writing Testable Code
 *
 * Shows the single most common thing that makes code UNTESTABLE - reaching
 * out to global/ambient state (LocalDate.now(), new Random(), a static
 * singleton) directly from inside business logic - and the fix: inject the
 * dependency instead, so a test can control it deterministically.
 */
public class TestableCodeDemo {

    // ---------------- BAD: hard to test ----------------
    // This method's output depends on "today", which changes every day and
    // cannot be controlled from a test. A test written against this method
    // would only pass on some days, or would need to sleep/wait, which is
    // exactly what a good unit test must never do.
    static class FixedDepositMaturityCheckerBad {
        boolean isMatured(LocalDate depositDate, int termInMonths) {
            LocalDate maturityDate = depositDate.plusMonths(termInMonths);
            return LocalDate.now().isAfter(maturityDate); // hidden dependency on the real clock!
        }
    }

    // ---------------- GOOD: Clock is injected, so tests can control "now" ----------------
    static class FixedDepositMaturityCheckerGood {
        private final Clock clock;

        FixedDepositMaturityCheckerGood(Clock clock) {
            this.clock = clock; // production code passes Clock.systemDefaultZone()
        }

        boolean isMatured(LocalDate depositDate, int termInMonths) {
            LocalDate maturityDate = depositDate.plusMonths(termInMonths);
            LocalDate today = LocalDate.now(clock); // deterministic, testable
            return today.isAfter(maturityDate);
        }
    }

    public static void main(String[] args) {
        FixedDepositMaturityCheckerGood checker = new FixedDepositMaturityCheckerGood(
                Clock.fixed(LocalDate.of(2025, 6, 1).atStartOfDay(ZoneId.of("UTC")).toInstant(),
                        ZoneId.of("UTC")));

        // Because "now" is fixed, this always produces the same, testable result -
        // this exact pattern is what a JUnit test uses (see below in this project).
        System.out.println("Deposit from 2024-01-01, 12-month term, matured by fixed 2025-06-01? " +
                checker.isMatured(LocalDate.of(2024, 1, 1), 12));

        FixedDepositMaturityCheckerBad badChecker = new FixedDepositMaturityCheckerBad();
        System.out.println("Bad version uses the REAL today's date, so its result changes daily: " +
                badChecker.isMatured(LocalDate.of(2024, 1, 1), 12));
    }
}
