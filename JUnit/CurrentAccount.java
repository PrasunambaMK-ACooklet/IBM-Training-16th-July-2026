/**
 * A second concrete Account type, used mainly by the parameterized/nested
 * test demo to show testing two related implementations together.
 */
public class CurrentAccount extends Account {

    public static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(String accountNumber, Customer owner, double openingBalance) {
        super(accountNumber, owner, openingBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException(
                    "Withdrawal exceeds overdraft limit of " + OVERDRAFT_LIMIT);
        }
        // Overdraft allowed: adjust balance directly instead of calling
        // super.withdraw(), which forbids going below zero.
        adjustBalance(-amount);
    }

    @Override
    public double monthlyInterest() {
        return 0.0; // current accounts don't earn interest in this model
    }
}
