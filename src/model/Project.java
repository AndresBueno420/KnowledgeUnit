package model;
import java.util.Calendar;


public class Project {
    private static final int SIZE = 6;
    private String projectName;
    private String clientName;
    private Calendar initialDate;
    private Calendar endDate;
    private double budget;
    private String manager;
    private String managerPhone;
    private String clientPhone;
    private Stages[] stages;
    
// This is a constructor method for the `Project` class that takes in several parameters including the
// project name, client name, initial and end dates, budget, manager name and phone number, and client
// phone number. It initializes the corresponding instance variables with the values passed in as
// parameters and also initializes the `stages` array with a size of `SIZE` (which is set to 6).
    public Project(String projectName, String clientName,Calendar initialDate, Calendar endDate, double budget, String manager, String managerPhone, String clientPhone){
        this.projectName = projectName;
        this.clientName = clientName;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.budget = budget;
        this.manager = manager;
        this.managerPhone = managerPhone;
        this.clientPhone = clientPhone;
        stages = new Stages[SIZE];

    }
   /**
    * The function returns an array of Stages.
    * 
    * @return An array of objects of type Stages.
    */
    public Stages[] getStages(){
        return stages;

    }
   /**
    * This Java function returns the name of a project.
    * 
    * @return The method `getName()` is returning the value of the variable `projectName`.
    */
    public String getName(){
        return projectName;
    }

  /**
   * This function returns the index of the first null element in an array.
   * 
   * @return The method `getFirstValidPosition()` returns an integer value which represents the index
   * of the first null element in the `stages` array. If there are no null elements in the array, it
   * returns -1.
   */
    public int getFirstValidPosition(){
        int pos = -1;
        boolean flag = false;
        for(int i = 0; i < SIZE && !flag; i++ ){
            if(stages[i] == null){
                pos = i;
                flag = true;
            }
        }
        return pos;
    }
   /**
    * This function adds a stage to an array of stages at the first available position.
    * 
    * @param stage an object of the Stages class that is being added to an array called "stages". The
    * method "addStages" adds the given stage object to the first available position in the "stages"
    * array.
    */
    public void addStages(Stages stage){
        int pos = getFirstValidPosition();
        if(pos != -1){
            stages[pos] = stage;
        }
    }
    
}
