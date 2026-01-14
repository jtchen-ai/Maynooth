package experiment6_1;

public class RealImage implements Image{
    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk();
    }

    public void loadFromDisk(){
        System.out.println("Loading image "+fileName+" from disk");
    }
    @Override
    public void display() {
        System.out.println("Display image: "+fileName);
    }
}
