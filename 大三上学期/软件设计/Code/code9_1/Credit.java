package experiment9_1;

class Credit {
    public boolean hasGoodCredit(Customer customer){
        System.out.println("Checking customer credit...");
        return customer.getcreditScore() >= 700;
    }
}
