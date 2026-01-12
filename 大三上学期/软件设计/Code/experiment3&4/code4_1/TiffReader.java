package experiment4_1;

public class TiffReader extends ImageReader{
    @Override
    public void readImage(String fileName) {
        System.out.println("Reading a tiff file:"+fileName);
    }
}
