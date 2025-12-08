package atm;

import javax.swing.JOptionPane;

/**
 * The type Transactions.
 */
public class Transactions {

    /**
     * The Answer 1.
     */
    public int answer1, /**
     * The Answer 2.
     */
    answer2, /**
     * The Amount.
     */
    amount;
    /**
     * The Withdraw ok.
     */
    public boolean withdrawOK = true;
    /**
     * The Ba.
     */
    public BankAccount ba;

    /**
     * Instantiates a new Transactions.
     */
    public Transactions() {
        answer1 = 0;
        answer2 = 0;
        amount = 0;
        ba = new BankAccount(1000);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Transactions transaction = new Transactions();
        transaction.getInput();
        System.exit(0);
    }

    /**
     * Gets input.
     */
    public void getInput() {
        answer1 = JOptionPane.showConfirmDialog(
                null,
                "Make a Deposit?",
                null,
                JOptionPane.YES_NO_OPTION);

        if (answer1 == JOptionPane.YES_OPTION) {
            String depString = JOptionPane.showInputDialog("Enter amount:");
            amount = Integer.parseInt(depString);

            //  deposit an amount in the account
            ba.deposit(amount);
        }

        else if (answer1 == JOptionPane.NO_OPTION) {
            answer2 = JOptionPane.showConfirmDialog(
                    null,
                    "Make a Withdraw?",
                    null,
                    JOptionPane.YES_NO_OPTION);

            if (answer2 == JOptionPane.YES_OPTION) {
                String withString = JOptionPane.showInputDialog("Enter amount:");
                amount = Integer.parseInt(withString);

                //  withdraw an amount from the account
                withdrawOK = ba.withdraw(amount);
            }
        }

        if (!withdrawOK) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your Balance = " + ba.getBalance()
                            + " which is not enough for this withdraw");
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Your balance is " + ba.getBalance());
        }
    }
}