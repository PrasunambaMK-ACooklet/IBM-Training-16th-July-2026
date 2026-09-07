import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TOPIC: TestContainers for Database/Integration Tests
 *
 * Requires (Maven):
 *   <dependency>
 *     <groupId>org.testcontainers</groupId>
 *     <artifactId>junit-jupiter</artifactId>
 *     <version>1.19.7</version>
 *     <scope>test</scope>
 *   </dependency>
 *   <dependency>
 *     <groupId>org.testcontainers</groupId>
 *     <artifactId>postgresql</artifactId>
 *     <version>1.19.7</version>
 *     <scope>test</scope>
 *   </dependency>
 *   <dependency>
 *     <groupId>org.postgresql</groupId>
 *     <artifactId>postgresql</artifactId>
 *     <version>42.7.3</version>
 *     <scope>test</scope>
 *   </dependency>
 *
 * Also requires a working Docker daemon on the machine running the tests -
 * Testcontainers starts a REAL, disposable PostgreSQL server in a Docker
 * container just for this test class, then throws it away afterwards.
 *
 * This is the strongest, most realistic kind of test for JdbcAccountRepository:
 * it runs actual SQL against an actual PostgreSQL engine, catching things a
 * mock or an in-memory fake never could - real JDBC types, driver quirks,
 * SQL dialect issues, and constraint violations.
 */
@Testcontainers
@DisplayName("JdbcAccountRepository against a real PostgreSQL container")
class AccountRepositoryTestContainersTest {

    // @Container: Testcontainers manages this container's lifecycle
    // automatically (starts it before tests, stops it after).
    @Container
    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("banking_test")
            .withUsername("test")
            .withPassword("test");

    private JdbcAccountRepository repository;

    @BeforeAll
    static void containerIsRunning() {
        assertTrue(postgres.isRunning(), "The PostgreSQL test container should be up before any test runs");
    }

    @BeforeEach
    void setUp() {
        DataSource dataSource = buildDataSource();
        repository = new JdbcAccountRepository(dataSource);
        repository.createSchemaIfNotExists();
    }

    private DataSource buildDataSource() {
        // A minimal, dependency-free DataSource pointed at the running
        // container's dynamically assigned JDBC URL/port.
        return new DataSource() {
            @Override
            public java.sql.Connection getConnection() throws java.sql.SQLException {
                return java.sql.DriverManager.getConnection(
                        postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
            }

            @Override
            public java.sql.Connection getConnection(String username, String password) throws java.sql.SQLException {
                return java.sql.DriverManager.getConnection(postgres.getJdbcUrl(), username, password);
            }

            @Override public java.io.PrintWriter getLogWriter() { return null; }
            @Override public void setLogWriter(java.io.PrintWriter out) { }
            @Override public void setLoginTimeout(int seconds) { }
            @Override public int getLoginTimeout() { return 0; }
            @Override public java.util.logging.Logger getParentLogger() { return null; }
            @Override public <T> T unwrap(Class<T> iface) { return null; }
            @Override public boolean isWrapperFor(Class<?> iface) { return false; }
        };
    }

    @Test
    @DisplayName("saving then finding an account round-trips through a real database")
    void saveAndFindRoundTrip() {
        Customer customer = new Customer("C-30", "Container User");
        Account account = new SavingsAccount("SB-6001", customer, 25000.0);

        repository.save(account);

        Optional<Account> found = repository.findByAccountNumber("SB-6001");
        assertTrue(found.isPresent());
        assertEquals(25000.0, found.get().getBalance(), 0.001);
        assertEquals("Container User", found.get().getOwner().getFullName());
    }

    @Test
    @DisplayName("finding a non-existent account returns empty, not an exception")
    void findMissingAccountReturnsEmpty() {
        Optional<Account> found = repository.findByAccountNumber("SB-DOES-NOT-EXIST");
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("saving twice with the same account number updates the balance (upsert)")
    void savingTwiceUpdatesBalance() {
        Customer customer = new Customer("C-31", "Upsert User");
        Account account = new SavingsAccount("SB-6002", customer, 1000.0);
        repository.save(account);

        account.deposit(500.0);
        repository.save(account); // same account number -> should UPDATE, not duplicate

        Optional<Account> found = repository.findByAccountNumber("SB-6002");
        assertEquals(1500.0, found.orElseThrow().getBalance(), 0.001);
    }
}
