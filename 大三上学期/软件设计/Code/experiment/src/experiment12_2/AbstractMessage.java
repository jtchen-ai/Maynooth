package experiment12_2;

public abstract class AbstractMessage {
    protected MessageImplementor impl;

    public AbstractMessage(MessageImplementor impl){
        this.impl = impl;
    }

    public void sendMessage(String message, String receiver){
        impl.send(message, receiver);
    }
}
