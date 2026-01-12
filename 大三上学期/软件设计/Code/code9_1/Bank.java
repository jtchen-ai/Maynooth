package experiment9_1;

class Bank {
    public boolean hasEnoughDeposits(Customer customer, double amount){
        System.out.println("Checking bank for sufficient savings...");
        return customer.getDeposits() >= amount;
    }
}
