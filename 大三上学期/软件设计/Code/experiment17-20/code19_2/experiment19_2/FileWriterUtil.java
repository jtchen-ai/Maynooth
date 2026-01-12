package experiment19_2;

class FileWriterUtil {
    private String filename;
    private String content;
    public FileWriterUtil(String filename){
        this.filename = filename;
        this.content = "";
    }
    public void write(String content){
        this.content = content;
    }
    public FileWriterUtilMemento save(){
        return new FileWriterUtilMemento(filename,content);
    }
    public void restore(FileWriterUtilMemento fileWriterUtilMemento){
        this.filename = fileWriterUtilMemento.filename;
        this.content = fileWriterUtilMemento.content;
    }
    public String getFilename(){
        return filename;
    }
    public String getContent(){
        return content;
    }
    class FileWriterUtilMemento{
        private String filename;
        private String content;
        public FileWriterUtilMemento(String filename, String content){
            this.filename = filename;
            this.content = content;
        }
        private String getFilename(){
            return filename;
        }
        private String getContent(){
            return content;
        }


    }
}
