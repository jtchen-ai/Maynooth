package experiment19_2;

public class Test {
    public static void main(String[] args) {
        FileWriterCaretaker fileWriterCaretaker = new FileWriterCaretaker();
        FileWriterUtil fileWriterUtil = new FileWriterUtil("testFile.txt");

        fileWriterUtil.write("Hello World!");
        System.out.println("Current Content: " + fileWriterUtil.getContent());

        fileWriterCaretaker.saveState(fileWriterUtil);

        fileWriterUtil.write("Hello World again!");
        System.out.println("Updated Content: " + fileWriterUtil.getContent());

        fileWriterCaretaker.restoreState(fileWriterUtil);
        System.out.println("After restoration, Content: " + fileWriterUtil.getContent());
    }
}
