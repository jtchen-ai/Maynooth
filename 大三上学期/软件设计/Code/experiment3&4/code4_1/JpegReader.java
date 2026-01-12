package experiment4_1;

public class JpegReader extends ImageReader{
    @Override
    public void readImage(String fileName) {
        System.out.println("Reading a jpeg file:"+fileName);
    }
}
