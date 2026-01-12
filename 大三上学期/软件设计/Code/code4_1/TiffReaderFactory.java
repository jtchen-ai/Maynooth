package experiment4_1;

public class TiffReaderFactory extends ImageReaderFactory{
    @Override
    public ImageReader createImageReader() {
        return new TiffReader();
    }
}
