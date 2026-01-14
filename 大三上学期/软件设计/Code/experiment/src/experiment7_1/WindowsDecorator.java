package experiment7_1;

class WindowsDecorator extends Decorator{
    public void setWindows(){
        System.out.println("Installing Windows operating system to the computer...");
    }

    @Override
    public void run() {
        super.run();
        setWindows();
    }
}
