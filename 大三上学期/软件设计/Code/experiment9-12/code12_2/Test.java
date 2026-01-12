package experiment12_2;

public class Test {
    public static void main(String[] args) {
        MessageImplementor impl = new MessageSMS();
        AbstractMessage m = new CommonMessage(impl);
        m.sendMessage("drink coffee", "A");
        m = new UrgentMessage(impl);
        m.sendMessage("drink coffee", "A");
        m = new SpecialUrgencyMessage(impl);
        m.sendMessage("drink coffee", "A");

        impl = new MessageEmail();
        m = new CommonMessage(impl);
        m.sendMessage("drink tea", "B");
        m = new UrgentMessage(impl);
        m.sendMessage("drink tea", "B");
        m = new SpecialUrgencyMessage(impl);
        m.sendMessage("drink tea", "B");
    }
}
