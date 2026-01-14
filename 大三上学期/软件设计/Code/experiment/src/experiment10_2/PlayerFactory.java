package experiment10_2;

import java.util.HashMap;

class PlayerFactory {
    public static final HashMap<String, Player> hm = new HashMap<>();
    public static synchronized Player getPlayer(String type){
        Player p = hm.get(type);
        if(p == null){
            switch (type){
                case "Terrorist":
                    System.out.println("Creating Terrorist...");
                    p = new Terrorist();
                    break;
                case "CounterTerrorist":
                    System.out.println("Creating CounterTerrorist...");
                    p = new CounterTerrorist();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown player type: " + type);
            }
            hm.put(type, p);
        }else{
            System.out.println("Using existing " + type + "player");
        }
        return p;
    }
}
