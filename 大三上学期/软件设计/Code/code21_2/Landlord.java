package experiment21_2;

class Landlord extends Person{
    public Landlord(String name, Mediator mediator){
        super(name, mediator);
    }

    @Override
    public void contact(String message) {
        System.out.println("Landlord " + name + " sends message: " + message);
        mediator.contact(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Landlord " + name + " gets message: " + message);
    }
}
