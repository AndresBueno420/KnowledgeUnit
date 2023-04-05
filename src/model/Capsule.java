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
    private Status approve;
    
    public void registerCapsule( String description, Type type, String nameEmployee, String chargeEmployee, String lessonLearned, Status aprove ){
        this.id = ID;
        ID++;
        this.description = description;
        this.type = type;
        this.nameEmployee = nameEmployee;
        this.chargeEmployee = chargeEmployee;
        this.lessonLearned = lessonLearned;
        this.published = false;
        this.approve = DENIED;
        
    }

    
}
