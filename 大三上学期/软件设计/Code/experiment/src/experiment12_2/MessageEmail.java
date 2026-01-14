package experiment12_2;

class MessageEmail implements MessageImplementor{
    @Override
    public void send(String message, String receiver) {
        System.out.println("Using email to send "+message+" to "+receiver);
    }
}
