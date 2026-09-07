import java.util.Optional;

/**
 * Abstraction over account storage. Having this as an INTERFACE (rather
 * than TransactionService talking to a database directly) is exactly what
 * makes TransactionService easy to unit test with a Mockito mock, and easy
 * to integration-test with either an in-memory fake or a real database via
 * Testcontainers.
 */
public interface AccountRepository {

    Optional<Account> findByAccountNumber(String accountNumber);

    void save(Account account);
}
