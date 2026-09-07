import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TOPIC: Mocking with Mockito
 *
 * Requires (Maven):
 *   <dependency>
 *     <groupId>org.mockito</groupId>
 *     <artifactId>mockito-junit-jupiter</artifactId>
 *     <version>5.11.0</version>
 *     <scope>test</scope>
 *   </dependency>
 *
 * TransactionService depends on AccountRepository and NotificationService
 * (both interfaces - see TestableCodeDemo.java for why that matters). Here
 * we replace BOTH real collaborators with Mockito mocks so this test:
 *   - never touches a real database,
 *   - never sends a real notification,
 *   - runs in milliseconds,
 *   - and can verify things a real collaborator wouldn't easily let us
 *     verify, like "was notify() called exactly once, with this message?"
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceMockitoTest {

    @Mock
    private AccountRepository accountRepository; // fake, controlled stand-in

    @Mock
    private NotificationService notificationService; // fake, controlled stand-in

    @InjectMocks
    private TransactionService transactionService; // real class under test, mocks injected automatically

    private SavingsAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount("SB-4001", new Customer("C-10", "Mockito User"), 5000.0);
    }

    @Test
    void depositSavesAccountAndSendsNotification() {
        // ---- Stub: tell the mock what to return when called ----
        when(accountRepository.findByAccountNumber("SB-4001")).thenReturn(Optional.of(account));

        transactionService.depositTo("SB-4001", 1000.0);

        assertEquals(6000.0, account.getBalance(), 0.001);

        // ---- Verify: confirm the mocks were actually used as expected ----
        verify(accountRepository, times(1)).save(account);
        verify(notificationService, times(1))
                .notify(eq("SB-4001"), anyString());
    }

    @Test
    void withdrawFromUnknownAccountThrowsAndNeverNotifies() {
        when(accountRepository.findByAccountNumber("SB-9999")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> transactionService.withdrawFrom("SB-9999", 100.0));

        // The account was never found, so nothing should have been saved or notified.
        verify(accountRepository, never()).save(org.mockito.ArgumentMatchers.any());
        verify(notificationService, never()).notify(anyString(), anyString());
    }

    @Test
    void transferNotifiesBothAccountsWithCapturedMessages() throws InsufficientFundsException {
        SavingsAccount fromAccount = new SavingsAccount("SB-4002", new Customer("C-11", "From User"), 5000.0);
        SavingsAccount toAccount = new SavingsAccount("SB-4003", new Customer("C-12", "To User"), 1000.0);

        when(accountRepository.findByAccountNumber("SB-4002")).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber("SB-4003")).thenReturn(Optional.of(toAccount));

        transactionService.transfer("SB-4002", "SB-4003", 1500.0);

        assertEquals(3500.0, fromAccount.getBalance(), 0.001);
        assertEquals(2500.0, toAccount.getBalance(), 0.001);

        // ---- ArgumentCaptor: capture the actual arguments a mock was called with ----
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(notificationService, times(2)).notify(anyString(), messageCaptor.capture());

        var capturedMessages = messageCaptor.getAllValues();
        assertEquals(2, capturedMessages.size());
        org.junit.jupiter.api.Assertions.assertTrue(capturedMessages.get(0).contains("Transferred"));
        org.junit.jupiter.api.Assertions.assertTrue(capturedMessages.get(1).contains("Received"));
    }
}
