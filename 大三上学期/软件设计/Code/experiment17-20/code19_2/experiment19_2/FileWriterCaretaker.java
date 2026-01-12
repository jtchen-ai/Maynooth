package experiment19_2;

class FileWriterCaretaker {
    private FileWriterUtil.FileWriterUtilMemento memento;
    public void saveState(FileWriterUtil fileWriterUtil){
        memento = fileWriterUtil.save();
    }
    public void restoreState(FileWriterUtil fileWriterUtil){
        fileWriterUtil.restore(memento);
    }
}
