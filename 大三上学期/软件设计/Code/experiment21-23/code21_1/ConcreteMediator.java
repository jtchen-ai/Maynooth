package experiment21_1;

class ConcreteMediator implements Mediator{
    private Colleague colleagueI;
    private Colleague colleagueII;

    public void setColleagueI(Colleague colleagueI){
        this.colleagueI = colleagueI;
    }
    public void setColleagueII(Colleague colleagueII){
        this.colleagueII = colleagueII;
    }

    @Override
    public void sendMsg(Colleague fromcolleague, String msg) {
        if(fromcolleague == colleagueI){
            colleagueII.receive(msg);
        }else if(fromcolleague == colleagueII){
            colleagueI.receive(msg);
        }
    }
}
