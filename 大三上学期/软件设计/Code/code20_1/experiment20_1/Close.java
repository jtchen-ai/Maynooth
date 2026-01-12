package experiment20_1;

class Close implements LiftState{
    @Override
    public void running(Lift lift) {
        System.out.println("The elevator is running.");
        lift.setLiftState(new Run());
    }

    @Override
    public void stopping(Lift lift) {
        System.out.println("The elevator is stopping.");
        lift.setLiftState(new Stop());
    }

    @Override
    public void opening(Lift lift) {
        System.out.println("The elevator door is opening.");
        lift.setLiftState(new Open());
    }

    @Override
    public void closing(Lift lift) {
        System.out.println("The elevator door is already closing.");
    }
}
