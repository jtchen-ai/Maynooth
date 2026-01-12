package experiment10_2;

class CounterTerrorist implements Player{
    private final String Task;
    private String Weapon;

    public CounterTerrorist(){
        Task = "Diffuse a bomb";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.Weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("CounterTerrorist with weapon: " + Weapon + " | Task: " + Task);
    }
}
