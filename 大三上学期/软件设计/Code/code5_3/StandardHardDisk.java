package experiment5_3;

public class StandardHardDisk implements HardDisk{
    @Override
    public void produceHardDisk() {
        System.out.print("Hard Disk: Standard Hard Disk");
    }

    @Override
    public double getPrice() {
        return 800;
    }
}
