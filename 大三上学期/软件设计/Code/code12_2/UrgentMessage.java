package experiment12_2;

class UrgentMessage extends AbstractMessage{
    public UrgentMessage(MessageImplementor impl){
        super(impl);
    }

    @Override
    public void sendMessage(String message, String receiver) {
        message += " urgent";
        impl.send(message, receiver);
    }
}
