package experiment20_1;

class Stop implements LiftState{
    @Override
    public void running(Lift lift) {
        System.out.println("The elevator is running.");
        lift.setLiftState(new Run());
    }

    @Override
    public void stopping(Lift lift) {
        System.out.println("The elevator is already stopped.");
    }

    @Override
    public void opening(Lift lift) {
        System.out.println("The elevator door is opening.");
        lift.setLiftState(new Open());
    }

    @Override
    public void closing(Lift lift) {
        System.out.println("The elevator door is closing.");
        lift.setLiftState(new Close());
    }
}
