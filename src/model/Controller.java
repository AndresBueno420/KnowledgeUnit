package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller {

    public static final int SIZE_PROJECT = 10;
    private Project[] projects;
    

    public Controller(){
        projects = new Project[SIZE_PROJECT];
        
    }
   /**
    * This function adds a new project to an array of projects with the given project details.
    * 
    * @param projectName A string representing the name of the project.
    * @param clientName The name of the client for whom the project is being done.
    * @param initialDate A Calendar object representing the initial date of the project.
    * @param endDate The end date of the project, represented as a Calendar object.
    * @param budget The budget parameter is a double data type that represents the total amount of
    * money allocated for the project.
    * @param manager The name of the manager assigned to the project.
    * @param managerPhone The phone number of the project manager.
    * @param clientPhone The client's phone number for the project.
    */
    public void addProject(String projectName, String clientName,Calendar initialDate, Calendar endDate, double budget, String manager, String managerPhone, String clientPhone){
        Project project = new Project(projectName, clientName, initialDate, endDate, budget, manager, managerPhone, clientPhone);
        int pos = getFirstValidPosition();
        if(pos != -1){
            projects[pos] = project;
        }
    }
    /**
     * This function returns the index of the first null element in an array of projects.
     * 
     * @return The method is returning an integer value, which is the first valid position in an array
     * called "projects". If there are no null values in the array, the method will return -1.
     */
    public int getFirstValidPosition(){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < SIZE_PROJECT && !isFound; i++){
			if(projects[i] == null){
				pos = i; 
				isFound = true;
			}
		}
		return pos; 
	}

    /**
     * This function initializes stages for a project with given start dates and adds them to the
     * project.
     * 
     * @param projectName a String representing the name of the project to be initialized
     * @param beginDate The planned start date of the project's stages.
     * @param realBeginDate A Calendar object representing the actual start date of the project.
     */
    public void initializeStages(String projectName, Calendar beginDate, Calendar realBeginDate)throws Exception{

        String controllerDate = "01/01/2011";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        boolean flag = false;

        Calendar startDates = Calendar.getInstance();
        startDates.setTime(format.parse(controllerDate));
        Calendar realStartDates = Calendar.getInstance();
        realStartDates.setTime(format.parse(controllerDate));

        for(int i = 0; i < SIZE_PROJECT && !flag; i++){
            if(projects[i] != null){
                if(projects[i].getName().equals(projectName)){
                    Stages startStage = new Stages(beginDate, realBeginDate);
                    boolean stageStatus = startStage.getActive();
                    if(stageStatus == false){
                        stageStatus = true;
                        startStage.setActive(stageStatus);
                    }
                    projects[i].addStages(startStage);

                    Stages analysisStage = new Stages(beginDate, realBeginDate);
                    projects[i].addStages(analysisStage);

                    Stages designStage = new Stages(beginDate, realBeginDate);
                    projects[i].addStages(designStage);

                    Stages executionStage = new Stages(beginDate, realBeginDate);
                    projects[i].addStages(executionStage);

                    Stages closeStage = new Stages(beginDate, realBeginDate);
                    projects[i].addStages(closeStage);

                    Stages followAndControlStage = new Stages(beginDate, realBeginDate);
                    projects[i].addStages(followAndControlStage);

                }
            }

        }

    }

  
  /**
   * This function adds a capsule to a specific stage in a project, given the project name, capsule
   * information, and the current active stage.
   * 
   * @param projectName The name of the project to which the capsule is being added.
   * @param capsuleId A unique identifier for the capsule being added.
   * @param type The type of the capsule being added (e.g. "technical", "management", "communication",
   * etc.).
   * @param capsuledescription A description of the capsule being added to the project.
   * @param nameEmployee The name of the employee who created the capsule.
   * @param chargeEmployee The chargeEmployee parameter is likely referring to the person who is
   * responsible for overseeing or managing the capsule being added to the project.
   * @param lessonLearned A string that represents the lesson learned from the capsule. It could be a
   * summary of the experience gained or the knowledge acquired from the capsule.
   */
    public void addCapsule(String projectName, String capsuleId, String type, String capsuledescription, String nameEmployee, String chargeEmployee, String lessonLearned ){

        boolean projectFound = false;
        boolean stageFound = false;
        boolean activatedState = false;

        Capsule capsule = new Capsule(capsuleId, capsuledescription, type, nameEmployee, chargeEmployee, lessonLearned);
        for(int i = 0; i < SIZE_PROJECT && !projectFound ; i++){
            if(projects[i].getName().equals(projectName)){
                Stages[] stages = projects[i].getStages();
                projectFound = true;
                for(int z = 0; z < stages.length && !stageFound; z++){
                    if(stages[z].getActive() == true){
                        activatedState = true;
                        stages[z].addCapsule(activatedState, capsule);
                        stageFound = true;
                    }

                }
            }
        }
    }

    /**
     * The function approves or disapproves a capsule in a specific project and stage, and sets the
     * publish date if approved.
     * 
     * @param projectName A String representing the name of the project to which the capsule belongs.
     * @param capsuleId A unique identifier for the capsule that needs to be approved or disapproved.
     * @param publishDate A Calendar object representing the date when the capsule is approved.
     * @param newStatus A boolean value indicating whether the capsule should be approved or not. If
     * true, the capsule will be approved and its publish date will be set to the provided date. If
     * false, the capsule will remain unapproved.
     * @return The method is returning a String message. The message will either indicate that the
     * capsule has been approved and provide the approval date, or indicate that the capsule remains
     * unapproved.
     */
    public String approveCapsule(String projectName, String capsuleId, Calendar publishDate, boolean newStatus){

        boolean projectFound = false;
        boolean stageFound = false; 
        boolean foundCapsule = false; 
        String msg = "";

        for(int i = 0; i < SIZE_PROJECT && !projectFound; i++){
            if(projects[i].getName().equals(projectName)){
                Stages[] stages = projects[i].getStages();
                projectFound = true;
                for(int x = 0; x < 6 && !stageFound; x++ ){
                    if(stages[x].getActive() == true){
                        Capsule[] capsules = stages[x].getCapsules();
                        stageFound = true;
                        for(int z = 0; z < capsules.length && !foundCapsule; z++){
                            if(capsules[z].getId().equals(capsuleId)){
                                if(newStatus == true){
                                    capsules[z].setApprove(newStatus);
                                    capsules[z].setPublishDate(publishDate);
                                    msg = "The capsule" + " " + capsuleId + "has been changed." + "Approve date: " + publishDate; 
                                    foundCapsule = true;
                                }
                                else{
                                    msg = "The capsule continues innappoved";
                                }
    
                            }
                        }
                    }
    
                }
                

            }
           
        }
            return msg;

    }

   /**
    * The function publishes a capsule with a given ID and project name if it is approved and not
    * already published.
    * 
    * @param projectName A String representing the name of the project to which the capsule belongs.
    * @param capsuleId A unique identifier for the capsule that needs to be published.
    * @param newPublishedStatus A boolean value indicating whether the capsule should be published or
    * not. If true, the capsule will be published. If false, it will not be published.
    * @return The method is returning a String message. The content of the message depends on the
    * conditions met during the execution of the method.
    */
    public String publishCapsule(String projectName, String capsuleId, boolean newPublishedStatus){

        boolean projectFound = false;
        boolean stageFound = false; 
        boolean capsuleFound = false;
        String msg = "";

        for(int i = 0; i < SIZE_PROJECT && !projectFound; i++){
            if(projects[i].getName().equals(projectName)){
                Stages[] stages = projects[i].getStages();
                projectFound = true;

                for(int x = 0; x < stages.length && !stageFound; x++){
                    if(stages[x].getActive() == true){
                        Capsule[] capsules = stages[x].getCapsules();
                        stageFound = true;
                        for(int z = 0; z < capsules.length && !capsuleFound; z++){
                            if(capsules[z].getId().equals(capsuleId)){
                                if(capsules[z].getApprove() == true){
                                    if(capsules[z].getPublished() == false){
                                        capsules[z].setApprove(newPublishedStatus);
                                        capsuleFound = true;
                                        msg = " https/GreenSQAunit" + capsuleId + ".net";
                                    }
                                    else{
                                        msg = "The capsule is already published.";
                                    }

                                }
                                else{
                                    msg = "Cannot publish a capsule that is not approved.";
                                }
                            }
                            else{
                                msg = "Couldnt find the capsule.";
                            }
                        }

                    }

                    }

            }
        }
                return msg;
    }

  /**
   * This function finishes a stage of a project by updating the end date and start date of the next
   * stage.
   * 
   * @param projectName A String representing the name of the project to finish a stage for.
   * @param endDate A Calendar object representing the end date of the current stage.
   * @param amountMonths The amount of months to be added to the end date of the current stage to set
   * the start date of the next stage. However, this parameter is not used in the method
   * implementation.
   */
    public void finishStage(String projectName, Calendar endDate, int amountMonths){

		boolean isFoundProject = false;

		for(int o = 0; o < SIZE_PROJECT && !isFoundProject; o++){
			if(projects[o].getName().equals(projectName)){
				Stages[] stages = projects[o].getStages();
				isFoundProject = true;
				for(int i = 0; i < stages.length && !stages[i].active; i++){
					if(i < stages.length){
						stages[i].active = false;
					}
					if(i < stages.length - 1){
						stages[i+1].active = true;
						Calendar newStartDate = stages[i].getRealEndDate();
						stages[i+1].setBeginDate(endDate);
						stages[i+1].setRealStartDate(newStartDate);
					}
				}
			}
            else{
                System.out.println("No project found under that name.");
            }
		}


	}

    
   /**
    * This function checks if there is at least one project in an array of projects.
    * 
    * @return The method is returning a boolean value, which indicates whether all the elements in the
    * projects array are not null. If all elements are not null, the method returns true, otherwise, it
    * returns false.
    */
    public boolean checkProject(){
        boolean flag = false;
        for(int i = 0; i < SIZE_PROJECT && !flag; i++){
            if(projects[i] != null){
                flag = true;
            }
            else{
                flag = false;
            }
        }
        return flag;
    }
    
}
