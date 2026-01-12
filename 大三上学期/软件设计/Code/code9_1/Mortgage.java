package experiment9_1;

class Mortgage {
    Bank enoughDeposits;
    Credit goodCredit;
    Loan badLoanRecords;

    public Mortgage(){
        enoughDeposits = new Bank();
        goodCredit = new Credit();
        badLoanRecords = new Loan();
    }

    public boolean isEligible(Customer customer, double loanAmount){
        System.out.println("Evaluating eligibility for " + customer.getName() + "...");
        boolean con1 = enoughDeposits.hasEnoughDeposits(customer, loanAmount);
        boolean con2 = goodCredit.hasGoodCredit(customer);
        boolean con3 = !badLoanRecords.hasBadLoanRecords(customer);
        return con1&&con2&&con3;
    }
}
