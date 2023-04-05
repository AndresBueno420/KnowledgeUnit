package model;
import java.util.Calendar;

public class Capsule {
    static int ID = 0;
    private int id;
    private String description;
    private Type type; 
    private String nameEmployee;
    private String chargeEmployee;
    private String lessonLearned;
    private boolean published;
    private Calendar publishDate;
    private boolean approveState;
    
    public void registerCapsule( String description, Type type, String nameEmployee, String chargeEmployee, String lessonLearned){
        this.id = ID;
        ID++;
        this.description = description;
        this.type = type;
        this.nameEmployee = nameEmployee;
        this.chargeEmployee = chargeEmployee;
        this.lessonLearned = lessonLearned;
        this.published = false;
        this.approveState = false;
        
    }
    public int getId(){
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
    public Type getType(){
        return type;
    }
    public void setType(Type type){
        this.type = type;
    }
    public void setApprove(boolean newApprove){
        this.approveState = newApprove;
    }
    public void setPublished(boolean newStatus){
        this.published = newStatus;
    }

     

    
}
