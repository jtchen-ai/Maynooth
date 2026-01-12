package experiment10_2;

import java.util.Random;

public class Test {
    private static final String[] playerTypes = {"Terrorist", "CounterTerrorist"};
    private static final String[] weapons = {"AK-47", "M4A1", "Sniper", "Pistol", "Knife"};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Player p = PlayerFactory.getPlayer(getRandPlayerType());
            p.assignWeapon(getRandWeapon());
            p.mission();
        }
    }
    public static String getRandPlayerType(){
        Random r = new Random();
        return playerTypes[r.nextInt(playerTypes.length)];
    }
    public static String getRandWeapon(){
        Random r = new Random();
        return weapons[r.nextInt(weapons.length)];
    }
}
