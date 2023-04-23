package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Stages {
    
private static final int SIZE_CAPSULE = 50;
private String name;
private Calendar beginDate;
private Calendar endDate;
private Calendar realBeginDate;
private Calendar realendDate;
public boolean active;
public boolean approve;
private Capsule[] capsules;

// This is a constructor method for the `Stages` class that takes two `Calendar` objects as parameters:
// `beginDate` and `realBeginDate`. It initializes the `beginDate` and `realBeginDate` instance
// variables with the values passed as parameters, and sets the `active` and `approve` instance
// variables to `false`. It also creates a new array of `Capsule` objects with a size of `SIZE_CAPSULE`
// and assigns it to the `capsules` instance variable.

public Stages(String name, Calendar beginDate, Calendar realBeginDate){

    this.name = name;
    this.beginDate = beginDate;
    this.realBeginDate = realBeginDate;
    this.active = false;
    this.approve = false;
    capsules = new Capsule[SIZE_CAPSULE];
}

/**
 * This function returns the approve state as a boolean value.
 * 
 * @return The method is returning the value of the boolean variable "approve".
 */
    public boolean getApproveState(){
        return approve;
}

 /**
  * The function returns an array of Capsule objects.
  * 
  * @return An array of Capsule objects is being returned.
  */
    public Capsule[] getCapsules(){
        return capsules;
}


 /**
  * The function adds a capsule to an array if a certain condition is met.
  * 
  * @param active A boolean value indicating whether the capsule is active or not.
  * @param capsule The "capsule" parameter is an object of the "Capsule" class, which is being passed
  * as an argument to the "addCapsule" method.
  */
    public void addCapsule(boolean active, Capsule capsule){

        if(active == true){
            int pos = getFirstValidPosition();
            if( pos != 1){
                capsules[pos] = capsule;
        }
    }
}

// The `getFirstValidPosition()` method is iterating through the `capsules` array and checking if each
// element is `null`. If it finds a `null` element, it sets the `pos` variable to the index of that
// element and sets the `flag` variable to `true` to exit the loop. If it does not find a `null`
// element, it returns `-1` to indicate that there is no valid position available in the array.
// Finally, it returns the `pos` variable, which contains the index of the first `null` element in the
// array.
    public int getFirstValidPosition(){
        int pos = -1;
        boolean flag = false;
        for(int i = 0; i < SIZE_CAPSULE && !flag; i++){
            if(capsules[i] == null){
                pos = i;
                flag = true;
        }
    }
        return pos;
}
 /**
  * The function returns the begin date as a Calendar object.
  * 
  * @return The method is returning a Calendar object named "beginDate".
  */
    public Calendar getBeginDate(){
        return beginDate;
}
 /**
  * This Java function sets a new value for the beginDate variable using a Calendar object.
  * 
  * @param newBeginDate A Calendar object representing the new beginning date to be set for a certain
  * task or event.
  */
    public void setBeginDate(Calendar newBeginDate){
        this.beginDate = newBeginDate;
    }
 /**
  * This function returns the end date of a calendar object.
  * 
  * @return The method is returning a Calendar object named "endDate".
  */
    public Calendar getEndDate(){
        return endDate;
}
   /**
    * This function sets the end date based on the number of months between stages.
    * 
    * @param monthsBetweenStages The number of months between the beginning date and the end date of a
    * stage. This parameter is used to calculate the end date of the stage based on the beginning date.
    */
     public void setEndDate(int monthsBetweenStages){
        Calendar beginDates = getBeginDate();
        Calendar endDates = (Calendar) beginDates.clone();
        endDates.add(Calendar.MONTH, monthsBetweenStages);
        this.endDate = endDates;
    }
   /**
    * This function returns a boolean value indicating whether or not something has been approved.
    * 
    * @return The method is returning a boolean value, which is the value of the variable "approve".
    */
    public boolean getApprove(){
        return approve;
    }
  /**
   * This Java function sets the value of a boolean variable called "approve".
   * 
   * @param approveStage a boolean value that represents whether a certain stage or action has been
   * approved or not. The method sets the value of the instance variable "approve" to the value of the
   * parameter "approveStage".
   */
    public void setApprove(boolean approveStage){
        this.approve = approveStage;
    }
   /**
    * This function returns the value of the boolean variable "active".
    * 
    * @return The method is returning a boolean value, which is the value of the variable "active".
    */
    public boolean getActive(){
        return active;
    }
   /**
    * The function sets the active status of an object in Java.
    * 
    * @param status a boolean value that represents the status of an object or variable. If status is
    * true, it means the object or variable is active, and if it is false, it means the object or
    * variable is inactive. The method setActive() sets the active status of an object or variable
    * based on the value of
    */
    public void setActive(boolean status){
        this.active = status;
    }
   /**
    * The function returns the real begin date as a Calendar object.
    * 
    * @return The method `getRealBeginDate()` is returning a `Calendar` object, specifically the
    * `realBeginDate` instance variable.
    */
    public Calendar getRealBeginDate(){
        return realBeginDate;
    }
   /**
    * This Java function sets the real start date of an object.
    * 
    * @param realStartDate realStartDate is a parameter of type Calendar that represents the actual
    * start date of a task or project. This method sets the value of the instance variable
    * realBeginDate to the value of the realStartDate parameter.
    */
    public void setRealStartDate(Calendar realStartDate){
        this.realBeginDate = realStartDate;
    }
    /**
     * The function returns the real end date as a Calendar object.
     * 
     * @return The method is returning a Calendar object named "realendDate".
     */
    public Calendar getRealEndDate(){
        return realendDate;
    }
   /**
    * This function sets the real end date of a task using a Calendar object.
    * 
    * @param endDate The parameter "endDate" is a Calendar object representing the date and time when a
    * task or project actually ended. This method sets the value of the "realendDate" instance variable
    * to the value of the "endDate" parameter.
    */
    public void setRealEndDate(Calendar endDate){
        this.realendDate = endDate;
    }
    
}
