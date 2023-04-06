package model;
import java.util.Calendar;

public class Capsule {
    private String id;
    private String description;
    private String type; 
    private String nameEmployee;
    private String chargeEmployee;
    private String lessonLearned;
    private boolean published;
    private Calendar publishDate;
    private boolean approveState;
    
    public Capsule( String id, String description, String type, String nameEmployee, String chargeEmployee, String lessonLearned){
        this.id = id;
        this.description = description;
        this.type = type;
        this.nameEmployee = nameEmployee;
        this.chargeEmployee = chargeEmployee;
        this.lessonLearned = lessonLearned;
        this.published = false;
        this.approveState = false;
        
    }
    public String getId(){
        return id;
    }
    public boolean getApprove(){
        return approveState;
    }
    public boolean getPublished(){
        return published;
    }
    public Calendar getPublishedDate(){
        return publishDate;
    }
    public void setPublishDate(Calendar publishDate){
        this.publishDate = publishDate;
    }
    //public Type getType(){
        //return type;
    //}
    //public void setType(Type type){
       // this.type = type;
    //}
    public void setApprove(boolean newApprove){
        this.approveState = newApprove;
    }
    public void setPublished(boolean newStatus){
        this.published = newStatus;
    }

     

    
}
