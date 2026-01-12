package experiment5_3;

public class ProHardDisk implements HardDisk{
    @Override
    public void produceHardDisk() {
        System.out.print("Hard Disk: Pro Hard Disk");
    }

    @Override
    public double getPrice() {
        return 800;
    }
}
