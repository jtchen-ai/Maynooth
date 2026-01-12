package experiment9_1;

class Client {
    public static void main(String[] args) {
        Customer customer = new Customer("Tim", 45000, 1000, 0);
        Mortgage mortgage = new Mortgage();
        boolean flag = mortgage.isEligible(customer, 2000);
        if(flag == true){
            System.out.println(customer.getName() + " is eligible for the loan.");
        }else{
            System.out.println(customer.getName() + " is not eligible for the loan.");
        }
    }
}
