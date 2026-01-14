package experiment21_2;

class MediatorStructure extends Mediator{
    private Person landlord;
    private Person renters;

    public void setLandlord(Person landlord) {
        this.landlord = landlord;
    }

    public void setRenters(Person renters) {
        this.renters = renters;
    }
    @Override
    public void contact(String message, Person person) {
        if(person == landlord){
            renters.getMessage(message);
        }else if(person == renters){
            landlord.getMessage(message);
        }
    }

}
