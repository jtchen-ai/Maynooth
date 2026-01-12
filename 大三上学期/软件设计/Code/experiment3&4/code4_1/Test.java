package experiment4_1;


public class Test {
    public static void main(String[] args) {
        ImageReaderFactory gifReaderFactory = new Gifreaderfactory();
        ImageReaderFactory jpegReaderFactory = new JpegReaderFactory();
        ImageReaderFactory tiffReaderFactory = new TiffReaderFactory();

        ImageReader gifReader = gifReaderFactory.createImageReader();
        ImageReader jpegReader = jpegReaderFactory.createImageReader();
        ImageReader tiffReader = tiffReaderFactory.createImageReader();

        gifReader.readImage("file.gif");
        jpegReader.readImage("file.jpeg");
        tiffReader.readImage("file.tiff");
    }
}
