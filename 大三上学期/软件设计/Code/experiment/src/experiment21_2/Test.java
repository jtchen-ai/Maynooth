package experiment21_2;

public class Test {
    public static void main(String[] args) {
        MediatorStructure mediatorStructure = new MediatorStructure();
        Landlord landlord = new Landlord("Lucy", mediatorStructure);
        Renters renter = new Renters("Jack", mediatorStructure);
        mediatorStructure.setLandlord(landlord);
        mediatorStructure.setRenters(renter);
        renter.contact("I want to rent a house.");
        landlord.contact("Ok, 2000 yuan per month");
    }
}
