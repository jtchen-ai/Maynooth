package experiment7_1;

class LinuxDecorator extends Decorator{
    public void setLinux(){
        System.out.println("Installing Linux operating system to the computer...");
    }

    @Override
    public void run() {
        super.run();// 这个super 调用的就是 machine.run  ,因为原方法调用 machine.run 然后，这边输入的machine 就是被装饰后的
        setLinux();
    }
}
