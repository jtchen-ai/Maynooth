package experiment3_2;

public class Human {
    private String head;
    private String body;
    private String hand;
    private String foot;

    public Human(Builder builder){
        this.head = builder.head;
        this.body = builder.body;
        this.hand = builder.hand;
        this.foot = builder.foot;
    }

    @Override
    public String toString(){
        return "Human{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", hand='" + hand + '\'' +
                ", foot='" + foot + '\'' +
                '}';
    }
}
