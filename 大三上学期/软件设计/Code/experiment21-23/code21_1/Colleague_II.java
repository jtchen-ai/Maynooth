package experiment21_1;

class Colleague_II extends Colleague{
    public Colleague_II(Mediator mediator){
        super(mediator);
    }

    @Override
    public void send(String msg) {
        System.out.println("Colleague II is sending message: " + msg);
        mediator.sendMsg(this, msg);
    }

    @Override
    public void receive(String msg) {
        System.out.println("Colleague I received message: " + msg);
    }
}
