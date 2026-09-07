/**
 * A second collaborator interface for TransactionService, alongside
 * AccountRepository. Having a real "side effect" (sending a message) live
 * behind an interface is what lets TransactionServiceMockitoTest verify
 * "was the customer notified?" without actually sending an email/SMS.
 */
public interface NotificationService {
    void notify(String accountNumber, String message);
}

/**
 * A trivial "real" implementation - not used by the unit tests directly
 * (they use a Mockito mock instead), but shown here so the interface has a
 * concrete production implementation somewhere in the codebase.
 */
class EmailNotificationService implements NotificationService {
    @Override
    public void notify(String accountNumber, String message) {
        System.out.println("EMAIL to account " + accountNumber + ": " + message);
    }
}
