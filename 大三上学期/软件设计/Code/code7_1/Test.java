package experiment7_1;

public class Test {
    public static void main(String[] args) {
        BareComputer bareComputer = new BareComputer();
        LinuxDecorator linuxDecorator = new LinuxDecorator();
        linuxDecorator.setMachine(bareComputer);
        linuxDecorator.run();

        System.out.println();

        WindowsDecorator windowsDecorator = new WindowsDecorator();
        windowsDecorator.setMachine(bareComputer);
        EclipseDecorator eclipseDecorator = new EclipseDecorator();
        eclipseDecorator.setMachine(windowsDecorator);
        eclipseDecorator.run();

    }
}
