package experiment16_1;

public class GameDemo {
    public static void main(String[] args) {
        GameTemplate footballGame = new FootballGame();
        GameTemplate cricketGame = new CricketGame();
        cricketGame.play();
        footballGame.play();
    }
}
