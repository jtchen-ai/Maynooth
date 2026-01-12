package experiment10_2;

import java.util.Random;

class CounterStrike {
    private static final String[] playerTypes = {"Terrorist", "CounterTerrorist"};
    private static final String[] weapons = {"AK-47", "M4A1", "Sniper", "Pistol", "Knife"};

    public void assignWeapon(String weapon){
        String playerType = playerTypes[new Random().nextInt(playerTypes.length)];
        Player player = PlayerFactory.getPlayer(playerType);
        player.assignWeapon(weapon);
        player.mission();
    }
    public void mission() {
        String weapon = weapons[new Random().nextInt(weapons.length)];
        assignWeapon(weapon);
    }
}
