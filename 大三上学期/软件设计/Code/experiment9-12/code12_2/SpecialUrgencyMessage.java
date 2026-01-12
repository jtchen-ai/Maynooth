package experiment12_2;

class SpecialUrgencyMessage extends AbstractMessage{
    public SpecialUrgencyMessage(MessageImplementor impl){
        super(impl);
    }

    @Override
    public void sendMessage(String message, String receiver) {
        message += " specially urgent";
        impl.send(message, receiver);
    }
}
