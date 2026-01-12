package experiment4_1;

public class JpegReaderFactory extends ImageReaderFactory{
    @Override
    public ImageReader createImageReader() {
        return new JpegReader();
    }
}
