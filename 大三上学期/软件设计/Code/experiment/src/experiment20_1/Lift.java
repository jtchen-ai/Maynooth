package experiment20_1;

class Lift {
    private LiftState liftState;
    public Lift(){
        this.liftState = new Close();
    }
    public void setLiftState(LiftState liftState){
        this.liftState = liftState;
    }
    public void run(){
        liftState.running(this);
    }
    public void stop(){
        liftState.stopping(this);
    }
    public void open(){
        liftState.opening(this);
    }
    public void close(){
        liftState.closing(this);
    }
}
