package experiment4_1;

public class GifReader extends ImageReader{
    @Override
    public void readImage(String fileName) {
        System.out.println("Reading a gif file:"+fileName);
    }
}
