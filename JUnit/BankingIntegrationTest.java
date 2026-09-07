import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TOPIC: Integration Testing Basics
 *
 * The difference from TransactionServiceMockitoTest is deliberate:
 * here NOTHING is mocked. TransactionService is wired up with REAL
 * collaborators - an actual InMemoryAccountRepository and an actual
 * NotificationService implementation - so the test exercises the whole
 * chain of real objects working together, the way they will in production.
 *
 * This catches a class of bug that unit tests with mocks structurally
 * cannot: mismatches in how two real classes actually interact (e.g. a
 * repository that silently drops a field the service needed).
 *
 * It's still fast and needs no external infrastructure because
 * InMemoryAccountRepository lives entirely in memory - contrast this with
 * AccountRepositoryTestContainersTest.java, which integrates with a real,
 * external database instead.
 */
@DisplayName("TransactionService wired with real (non-mocked) collaborators")
class BankingIntegrationTest {

    private AccountRepository accountRepository;
    private NotificationService notificationService;
    private TransactionService transactionService;

    private static final class RecordingNotificationService implements NotificationService {
        final java.util.List<String> sentMessages = new java.util.ArrayList<>();

        @Override
        public void notify(String accountNumber, String message) {
            sentMessages.add(accountNumber + ": " + message);
        }
    }

    @BeforeEach
    void setUp() {
        accountRepository = new InMemoryAccountRepository();
        notificationService = new RecordingNotificationService();
        transactionService = new TransactionService(accountRepository, notificationService);

        Customer customer = new Customer("C-20", "Integration User");
        accountRepository.save(new SavingsAccount("SB-5001", customer, 10000.0));
    }

    @Test
    @DisplayName("a deposit flows all the way through repository and notification")
    void depositEndToEnd() {
        transactionService.depositTo("SB-5001", 2500.0);

        Account updated = accountRepository.findByAccountNumber("SB-5001").orElseThrow();
        assertEquals(12500.0, updated.getBalance(), 0.001);

        RecordingNotificationService recorder = (RecordingNotificationService) notificationService;
        assertEquals(1, recorder.sentMessages.size());
        org.junit.jupiter.api.Assertions.assertTrue(recorder.sentMessages.get(0).contains("Deposit"));
    }

    @Test
    @DisplayName("a transfer between two real accounts updates both correctly")
    void transferEndToEnd() throws InsufficientFundsException {
        accountRepository.save(new SavingsAccount("SB-5002", new Customer("C-21", "Second User"), 3000.0));

        transactionService.transfer("SB-5001", "SB-5002", 4000.0);

        assertEquals(6000.0, accountRepository.findByAccountNumber("SB-5001").orElseThrow().getBalance(), 0.001);
        assertEquals(7000.0, accountRepository.findByAccountNumber("SB-5002").orElseThrow().getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdrawing on an unknown account fails before touching anything")
    void withdrawUnknownAccountFails() {
        assertThrows(IllegalArgumentException.class,
                () -> transactionService.withdrawFrom("SB-DOES-NOT-EXIST", 100.0));
    }
}
