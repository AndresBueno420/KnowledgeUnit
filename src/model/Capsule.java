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
    private Type capsuleType;
    
  
 // This is a constructor method for the Capsule class that takes in six parameters: id, description,
 // type, nameEmployee, chargeEmployee, and lessonLearned. It initializes the instance variables of the
 // Capsule object with the values passed in as parameters, and sets the published and approveState
 // variables to false.
    public Capsule( String id, String description, String nameEmployee, String chargeEmployee, String lessonLearned, Type capsuleType){
        this.id = id;
        this.description = description;
        this.capsuleType = capsuleType;
        this.nameEmployee = nameEmployee;
        this.chargeEmployee = chargeEmployee;
        this.lessonLearned = lessonLearned;
        this.published = false;
        this.approveState = false;
    
        
    }
   /**
    * The function returns the value of the "id" variable as a string.
    * 
    * @return The method `getId()` is returning a `String` value, which is the value of the variable
    * `id`.
    */
    public String getId(){
        return id;
    }
    /**
     * The function returns the approve state as a boolean value.
     * 
     * @return The method is returning a boolean value, specifically the value of the variable
     * `approveState`.
     */
    public boolean getApprove(){
        return approveState;
    }
   /**
    * The function returns the name of an employee.
    * 
    * @return The method `getEmployeeName()` is returning the value of the variable `nameEmployee`,
    * which is likely the name of an employee.
    */
    public String getEmployeeName(){
        return nameEmployee;
    }
   /**
    * This function returns a boolean value indicating whether an item has been published or not.
    * 
    * @return The method is returning a boolean value, specifically the value of the variable
    * "published".
    */
    public boolean getPublished(){
        return published;
    }
   /**
    * The function returns the publish date as a Calendar object.
    * 
    * @return The method `getPublishedDate()` is returning a `Calendar` object, which represents the
    * date and time of publication.
    */
    public Calendar getPublishedDate(){
        return publishDate;
    }
    /**
     * This function sets the publish date of an object using a Calendar object.
     * 
     * @param publishDate a variable of type Calendar that represents the date when something was
     * published. The method setPublishDate() sets the value of this variable.
     */
    public void setPublishDate(Calendar publishDate){
        this.publishDate = publishDate;
    }
    //public Type getType(){ Unused functions 
        //return type;
    //}
    //public void setType(Type type){
       // this.type = type;
    //}
    /**
     * This Java function sets the approve state to a new boolean value.
     * 
     * @param newApprove a boolean variable that represents the new approval state that we want to set
     * for an object.
     */
    public void setApprove(boolean newApprove){
        this.approveState = newApprove;
    }
    /**
     * This function sets the published status of an object to a new boolean value.
     * 
     * @param newStatus a boolean value that represents the new status to be set for the "published"
     * attribute of an object. If newStatus is true, it means the object is published, and if it's
     * false, it means the object is not published. The method sets the "published" attribute of the
     * object to
     */
    public void setPublished(boolean newStatus){
        this.published = newStatus;
    }

    public String getLessonLearned(){
        return lessonLearned;
    }

     

    
}
