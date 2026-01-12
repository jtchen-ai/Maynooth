package experiment21_2;

abstract class Person {
    protected String name;
    protected Mediator mediator;
    public Person(String name, Mediator mediator){
        this.name = name;
        this.mediator = mediator;
    }
    public String getName(){
        return name;
    }
    public Mediator getMediator(){
        return mediator;
    }
    public abstract void contact(String message);
    public abstract void getMessage(String message);
}
