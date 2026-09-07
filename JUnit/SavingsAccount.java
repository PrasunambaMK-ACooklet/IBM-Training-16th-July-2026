/**
 * Concrete Account used by most tests: enforces a minimum balance on
 * withdrawal and pays monthly interest - both are behaviours worth testing.
 */
public class SavingsAccount extends Account {

    public static final double MIN_BALANCE = 1000.0;
    public static final double ANNUAL_INTEREST_RATE_PERCENT = 4.0;

    public SavingsAccount(String accountNumber, Customer owner, double openingBalance) {
        super(accountNumber, owner, openingBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientFundsException(
                    "Withdrawal would breach minimum balance of " + MIN_BALANCE);
        }
        super.withdraw(amount);
    }

    @Override
    public double monthlyInterest() {
        return getBalance() * (ANNUAL_INTEREST_RATE_PERCENT / 100.0 / 12.0);
    }
}
