package experiment12_2;

class CommonMessage extends AbstractMessage{
    public CommonMessage(MessageImplementor impl){
        super(impl);
    }

    @Override
    public void sendMessage(String message, String receiver) {
        impl.send(message, receiver);
    }
}
