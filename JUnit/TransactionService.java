import java.util.Optional;

/**
 * TOPIC (supporting class for): Writing Testable Code, Mocking with Mockito
 *
 * TransactionService depends only on the AccountRepository and
 * NotificationService INTERFACES (constructor injection), never on a
 * concrete database or email client directly. That single design choice is
 * what makes this class:
 *   - trivial to UNIT test with Mockito mocks standing in for both
 *     collaborators (see TransactionServiceMockitoTest), and
 *   - trivial to INTEGRATION test with real, lightweight implementations
 *     (see BankingIntegrationTest).
 */
public class TransactionService {

    private final AccountRepository accountRepository;
    private final NotificationService notificationService;

    public TransactionService(AccountRepository accountRepository,
                               NotificationService notificationService) {
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
    }

    public void depositTo(String accountNumber, double amount) {
        Account account = getAccountOrThrow(accountNumber);
        account.deposit(amount);
        accountRepository.save(account);
        notificationService.notify(accountNumber, "Deposit of " + amount + " received.");
    }

    public void withdrawFrom(String accountNumber, double amount) throws InsufficientFundsException {
        Account account = getAccountOrThrow(accountNumber);
        account.withdraw(amount);
        accountRepository.save(account);
        notificationService.notify(accountNumber, "Withdrawal of " + amount + " processed.");
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws InsufficientFundsException {
        Account from = getAccountOrThrow(fromAccountNumber);
        Account to = getAccountOrThrow(toAccountNumber);

        from.withdraw(amount);
        to.deposit(amount);

        accountRepository.save(from);
        accountRepository.save(to);

        notificationService.notify(fromAccountNumber, "Transferred " + amount + " to " + toAccountNumber);
        notificationService.notify(toAccountNumber, "Received " + amount + " from " + fromAccountNumber);
    }

    private Account getAccountOrThrow(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("No such account: " + accountNumber);
        }
        return account.get();
    }
}
