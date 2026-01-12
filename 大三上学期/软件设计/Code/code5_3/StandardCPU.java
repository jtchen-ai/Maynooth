package experiment5_3;

public class StandardCPU implements CPU{
    @Override
    public void produceCPU() {
        System.out.print("CPU: Standard CPU");
    }

    @Override
    public double getPrice() {
        return 2000;
    }
}
