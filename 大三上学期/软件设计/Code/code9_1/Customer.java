package experiment9_1;

class Customer {
    private String name;
    private double deposits;
    private int creditScore;
    private int badLoanRecords;

    public Customer(String name, double deposits, int creditScore, int badLoanRecords){
        this.name = name;
        this.deposits = deposits;
        this.creditScore = creditScore;
        this.badLoanRecords = badLoanRecords;
    }

    public String getName(){
        return name;
    }
    public double getDeposits(){
        return deposits;
    }
    public int getcreditScore(){
        return creditScore;
    }
    public int getBadLoanRecords(){
        return badLoanRecords;
    }
}
