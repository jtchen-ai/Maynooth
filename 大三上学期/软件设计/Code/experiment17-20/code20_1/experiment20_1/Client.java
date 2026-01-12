package experiment20_1;

public class Client {
    public static void main(String[] args) {
        Lift list= new Lift ();
        list.open();
        list.close();
        list.run();
        list.stop();
    }
}
