package experiment10_2;

class Terrorist implements Player{
    private final String Task;
    private String Weapon;

    public Terrorist() {
        Task = "Plant a bomb";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.Weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("Terrorist with weapon: " + Weapon + " | Task: " + Task);
    }
}
