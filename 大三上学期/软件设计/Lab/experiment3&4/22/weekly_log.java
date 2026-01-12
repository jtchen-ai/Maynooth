package experiment2_2;

class WeeklyLog extends Prototype{
    private String name;
    private String date;
    private String content;
    private Attachment attachment;
    public WeeklyLog(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public String getDate(){
        return date;
    }

    public String getContent() {
        return content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    @Override
 public WeeklyLog clone() throws CloneNotSupportedException {
        return (WeeklyLog)super.clone();
 }
}

