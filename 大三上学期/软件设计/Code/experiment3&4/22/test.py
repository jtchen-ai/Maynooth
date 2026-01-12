package experiment2_2;

public class Test {
    public static void main(String args[]) throws CloneNotSupportedException {
        WeeklyLog log_previous, log_new;
        log_previous = new WeeklyLog(); //Create prototype object
        Attachment attachment = new Attachment(); //Create attachment object
        log_previous.setAttachment(attachment); //Add attachments to the weekly report
        log_new = log_previous.clone(); 
        System.out.println("Are the weekly reports the same? "+ (log_previous == log_new));
        //Compare attachments
        System.out.println("Are the attachments the same?" + (log_previous.getAttachment() == log_new.getAttachment()));
    }
}
