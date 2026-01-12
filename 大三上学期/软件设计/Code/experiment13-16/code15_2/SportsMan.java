package experiment15_2;

class SportsMan {
    protected IBall iBall;
    public void setBall(IBall ball) {
        this.iBall = ball;
    }
    public void show(){
        if (iBall != null) {
            iBall.play();
        } else {
            System.out.println("No ball selected.");
        }
    }
}
