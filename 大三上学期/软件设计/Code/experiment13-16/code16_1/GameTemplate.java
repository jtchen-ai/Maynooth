package experiment16_1;

abstract class GameTemplate {
    public final void play(){
        initialize();
        startPlay();
        endPlay();
    }
    public abstract void initialize();
    public abstract void startPlay();
    public abstract void endPlay();
}
