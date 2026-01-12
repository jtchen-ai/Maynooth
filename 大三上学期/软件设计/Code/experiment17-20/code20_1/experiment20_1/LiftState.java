package experiment20_1;

interface LiftState {
    void running(Lift lift);
    void stopping(Lift lift);
    void opening(Lift lift);
    void closing(Lift lift);
}
