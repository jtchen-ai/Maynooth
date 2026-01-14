package experiment7_1;

class EclipseDecorator extends Decorator{
    public void setEclipse(){
        System.out.println("Installing Eclipse to the computer...");
    }

    @Override
    public void run() {
        super.run();
        setEclipse();
    }
}
