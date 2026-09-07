import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

/**
 * TOPIC (supporting class for): TestContainers for Database/Integration Tests
 *
 * A REAL implementation of AccountRepository backed by plain JDBC and a
 * "accounts" table. This is exactly the kind of class that is pointless to
 * unit test with mocks (there'd be nothing left to verify except that we
 * called the mock JDBC driver correctly) - it needs to run against an
 * actual database, which is what AccountRepositoryTestContainersTest does
 * using a real, disposable PostgreSQL instance spun up by Testcontainers.
 */
public class JdbcAccountRepository implements AccountRepository {

    private final DataSource dataSource;

    public JdbcAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** Creates the schema this repository expects. Called once by tests/setup. */
    public void createSchemaIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
                "account_number VARCHAR(50) PRIMARY KEY, " +
                "customer_id VARCHAR(50) NOT NULL, " +
                "customer_name VARCHAR(200) NOT NULL, " +
                "balance DOUBLE PRECISION NOT NULL)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create schema", e);
        }
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        String sql = "SELECT account_number, customer_id, customer_name, balance " +
                "FROM accounts WHERE account_number = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                Customer customer = new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("customer_name"));
                Account account = new SavingsAccount(
                        resultSet.getString("account_number"),
                        customer,
                        resultSet.getDouble("balance"));
                return Optional.of(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to query account " + accountNumber, e);
        }
    }

    @Override
    public void save(Account account) {
        String sql = "INSERT INTO accounts (account_number, customer_id, customer_name, balance) " +
                "VALUES (?, ?, ?, ?) " +
                "ON CONFLICT (account_number) DO UPDATE SET balance = EXCLUDED.balance";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getAccountNumber());
            statement.setString(2, account.getOwner().getCustomerId());
            statement.setString(3, account.getOwner().getFullName());
            statement.setDouble(4, account.getBalance());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save account " + account.getAccountNumber(), e);
        }
    }
}
