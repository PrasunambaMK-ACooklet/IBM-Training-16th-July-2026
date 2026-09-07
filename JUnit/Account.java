/**
 * Production class under test for most of the demos in this folder.
 * Kept simple on purpose: the point of this folder is to show HOW to test
 * code like this, not to re-demonstrate every OOP concept again.
 */
public abstract class Account {

    private final String accountNumber;
    private final Customer owner;
    private double balance;

    protected Account(String accountNumber, Customer owner, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds in account " + accountNumber);
        }
        balance -= amount;
    }

    /**
     * Lets a subclass adjust the balance directly, bypassing the public
     * deposit()/withdraw() validation - used by CurrentAccount to allow an
     * overdrawn (negative) balance.
     */
    protected final void adjustBalance(double delta) {
        balance += delta;
    }

    public abstract double monthlyInterest();
}
