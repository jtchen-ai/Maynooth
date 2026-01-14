package experiment21_2;

class Renters extends  Person{
    public Renters(String name, Mediator mediator){
        super(name, mediator);
    }

    @Override
    public void contact(String message) {
        System.out.println("Renter " + name + " sends message: " + message);
        mediator.contact(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Renter " + name + " gets message: " + message);
    }
}
