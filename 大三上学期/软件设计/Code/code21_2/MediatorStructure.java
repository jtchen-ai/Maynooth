package experiment21_2;

class MediatorStructure extends Mediator{
    private Landlord landlord;
    private Renters renters;

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public void setRenters(Renters renters) {
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
