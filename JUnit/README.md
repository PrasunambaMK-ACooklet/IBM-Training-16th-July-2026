# Banking Application — Testing Concepts

A flat folder of Java files (no subfolders, no `package` declarations) so
every file can be dropped straight into a GitHub repo or a single Maven
project. `pom.xml` is included and pre-wired with all the dependencies
these files need (JUnit 5, Mockito, Testcontainers, JaCoCo).

## How to run

```
mvn test
```

For the code-coverage report:
```
mvn test jacoco:report
# open target/site/jacoco/index.html
```

`AccountRepositoryTestContainersTest.java` needs a running **Docker**
daemon (Testcontainers starts a real, disposable PostgreSQL container for
that one test class only). All other tests need nothing but the JVM.

## Topic → File index

| Topic | File(s) |
|---|---|
| Introduction to Unit Testing Concepts | `IntroToUnitTestingConceptsDemo.java` |
| JUnit 5 Basics (Annotations, Assertions, Lifecycle) | `AccountJUnit5BasicsTest.java` |
| Parameterized and Nested Tests | `AccountParameterizedNestedTest.java` |
| Mocking with Mockito | `TransactionServiceMockitoTest.java` |
| Writing Testable Code | `TestableCodeDemo.java` |
| Integration Testing Basics | `BankingIntegrationTest.java` |
| TestContainers for Database/Integration Tests | `AccountRepositoryTestContainersTest.java`, `JdbcAccountRepository.java` |
| Code Coverage Concepts | `CodeCoverageConceptsDemoTest.java`, `LoanEligibilityChecker.java` |
| Test-Driven Development (TDD) Overview | `TddLateFeeCalculatorTest.java`, `LateFeeCalculator.java` |

## Supporting production classes (used across the demos above)

| File | Purpose |
|---|---|
| `Account.java`, `SavingsAccount.java`, `CurrentAccount.java` | Core account domain model |
| `Customer.java` | Simple customer value object |
| `InsufficientFundsException.java` | Custom checked exception |
| `AccountRepository.java` | Storage abstraction (interface) — the seam that makes mocking/integration testing possible |
| `InMemoryAccountRepository.java` | Real, in-memory implementation — used by the integration test |
| `NotificationService.java` | Notification abstraction (interface) + a simple `EmailNotificationService` |
| `TransactionService.java` | Main service under test — depends only on the two interfaces above |
| `LoanEligibilityChecker.java` | Branch-heavy class used to illustrate coverage gaps |

## Why the interfaces matter

`TransactionService` never talks to a database or an email server
directly — it only knows about `AccountRepository` and
`NotificationService` as interfaces. That one design decision (see
`TestableCodeDemo.java`) is what makes it possible to:
- swap in **Mockito mocks** for a fast, isolated **unit test**
  (`TransactionServiceMockitoTest.java`), and
- swap in **real, lightweight implementations** for a true
  **integration test** (`BankingIntegrationTest.java`), and
- swap in a **real database via Testcontainers** for the strongest kind of
  integration test (`AccountRepositoryTestContainersTest.java`) —
  all without changing `TransactionService` itself.
