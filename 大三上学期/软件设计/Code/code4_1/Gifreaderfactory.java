package experiment4_1;

public class Gifreaderfactory extends ImageReaderFactory{
    @Override
    public ImageReader createImageReader() {
        return new GifReader();
    }
}
