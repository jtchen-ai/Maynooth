package experiment21_1;

class Colleague_I extends Colleague{
    public Colleague_I(Mediator mediator){
        super(mediator);
    }

    @Override
    public void send(String msg) {
        System.out.println("Colleague I is sending message: " + msg);
        mediator.sendMsg(this, msg);
    }

    @Override
    public void receive(String msg) {
        System.out.println("Colleague II received message: " + msg);
    }
}
