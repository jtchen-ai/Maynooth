package experiment5_3;

public class ProCPU implements CPU{
    @Override
    public void produceCPU() {
        System.out.print("CPU: Pro CPU");
    }

    @Override
    public double getPrice() {
        return 3000;
    }
}
