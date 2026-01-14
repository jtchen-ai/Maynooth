package experiment3_2;

public class Builder {
    protected String head;
    protected String body;
    protected String hand;
    protected String foot;

    public Builder addhead(String head){
        this.head = head;
        return this;
    }

    public Builder addbody(String body){
        this.body = body;
        return this;
    }

    public Builder addhand(String hand){
        this.hand = hand;
        return this;
    }

    public Builder addfoot(String foot){
        this.foot = foot;
        return this;
    }

    public Human build(){
        return new Human(this);
    }
}
