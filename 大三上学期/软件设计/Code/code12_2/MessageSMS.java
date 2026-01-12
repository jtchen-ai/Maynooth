package experiment12_2;

class MessageSMS implements MessageImplementor{
    @Override
    public void send(String message, String receiver) {
        System.out.println("Using SMS to send "+message+" to "+receiver);
    }
}
