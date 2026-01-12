package experiment9_1;

class Loan {
    public boolean hasBadLoanRecords(Customer customer){
        System.out.println("Checking loan history...");
        return customer.getBadLoanRecords() == 0;
    }
}
