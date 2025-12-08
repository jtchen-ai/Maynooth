package atm;

/**
 * The type Bank account.
 */
public class BankAccount {

    private double balance;

    /**
     * Instantiates a new Bank account.
     */
    public BankAccount() {
        balance = 0;
    }

    /**
     * Instantiates a new Bank account.
     *
     * @param initialBalance the initial balance
     */
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    /**
     * Deposit.
     *
     * @param amount the amount
     */
    public void deposit(double amount) {
        balance = balance + amount;
    }

    /**
     * Withdraw boolean.
     *
     * @param amount the amount
     * @return the boolean
     */
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
}