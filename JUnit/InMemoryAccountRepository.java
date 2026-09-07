import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A fast, real (not mocked) in-memory implementation of AccountRepository.
 * Used by BankingIntegrationTest to test TransactionService together with a
 * genuine collaborator instead of a mock - that's what makes it an
 * "integration" test rather than a "unit" test.
 */
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> storage = new HashMap<>();

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(storage.get(accountNumber));
    }

    @Override
    public void save(Account account) {
        storage.put(account.getAccountNumber(), account);
    }
}
