package experiment9_2;

class PointsGift {
    private String name;
    private int points;

    public PointsGift(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName(){
        return name;
    }
    public int getPoints(){
        return points;
    }
    public void setPoints(int points){
        this.points = points;
    }
}
