package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller {

    public static final int SIZE_PROJECT = 10;
    private Project[] projects;
    static int technicCounter = 0;
    static int managementCounter = 0;
    static int domainCounter = 0;
    static int experienceCounter = 0;
    

    public Controller(){
        projects = new Project[SIZE_PROJECT];
        
    }
  
   /**
    * This function adds a new project to the array of projects with the given project details.
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
    * This function initializes stages for a project with given start dates and duration.
    * 
    * @param projectName The name of the project to which the stages will be added.
    * @param beginDate The start date of the project's first stage.
    * @param realBeginDate The actual start date of the project, which may differ from the planned
    * start date (beginDate).
    * @param StageMonths The duration of each stage in months.
    */
    public void initializeStages(String projectName, Calendar beginDate, Calendar realBeginDate, int StageMonths)throws Exception{

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
                    Stages startStage = new Stages("Start", beginDate, realBeginDate);
                    boolean stageStatus = startStage.getActive();
                    if(stageStatus == false){
                        stageStatus = true;
                        startStage.setActive(stageStatus);
                        startStage.setEndDate(StageMonths);;
                    }
                    projects[i].addStages(startStage);

                    Stages analysisStage = new Stages("Analisys Stage" , beginDate, realBeginDate);
                    projects[i].addStages(analysisStage);

                    Stages designStage = new Stages( "Design", beginDate, realBeginDate);
                    projects[i].addStages(designStage);

                    Stages executionStage = new Stages("Execution", beginDate, realBeginDate);
                    projects[i].addStages(executionStage);

                    Stages closeStage = new Stages("Close", beginDate, realBeginDate);
                    projects[i].addStages(closeStage);

                    Stages followAndControlStage = new Stages("FollowAndControl", beginDate, realBeginDate);
                    projects[i].addStages(followAndControlStage);

                }
            }

        }

    }

  
  
   
    /**
     * This function adds a capsule to a specific project and stage, based on its
     * parameters.
     * 
     * @param projectName The name of the project to which the capsule will be added.
     * @param capsuleId A unique identifier for the capsule being added.
     * @param type An integer representing the type of capsule being added.
     * @param capsuledescription A description of the capsule being added.
     * @param nameEmployee The name of the employee who created the capsule.
     * @param chargeEmployee The chargeEmployee parameter is a String that represents the person in
     * charge of the capsule.
     * @param lessonLearned lessonLearned is a String parameter that represents the lesson learned from
     * the capsule being added.
     * @param hashtags hashtags are a string of keywords or phrases preceded by the pound sign (#) used
     * to categorize and organize content on social media platforms. In this context, it is used as a
     * parameter to addCapsule() method to add hashtags to a Capsule object.
     */
    public void addCapsule(String projectName, String capsuleId, int type, String capsuledescription, String nameEmployee, String chargeEmployee, String lessonLearned, String hashtags ){

        boolean projectFound = false;
        boolean stageFound = false;
        boolean activatedState = false;
        Type capsuleCategory = null;
      

        if(type == 1){
            capsuleCategory = Type.TECHNIC;
            technicCounter += 1;
        }
        else if(type == 2){
            capsuleCategory = Type.MANAGEMENT;
            managementCounter += 1;
        }
        else if(type == 3){
            capsuleCategory = Type.DOMAIN;
            domainCounter += 1;
        }
        else if(type == 4){
            capsuleCategory = Type.EXPERIENCE;
            experienceCounter += 1;
        }


        Capsule capsule = new Capsule(capsuleId, capsuledescription, nameEmployee, chargeEmployee, lessonLearned, capsuleCategory, hashtags);
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
                                else if (capsules[z].getApprove() == true){
                                    msg = "The capsule is already approved";
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
    * This function deactivates the current stage of a project and activates the next stage, while
    * setting the real end date of the current stage.
    * 
    * @param projectName A String representing the name of the project to finish a stage for.
    * @param endDate A Calendar object representing the actual end date of the current stage.
    * @param amountMonths The parameter "amountMonths" is not used in the given code snippet.
    * @return The method is returning a String message indicating that the current stage has been
    * deactivated and the next stage has been activated.
    */
    public String finishStage(String projectName, Calendar endDate, int amountMonths){

		boolean isFoundProject = false;
        boolean foundStage = true;
        boolean activeStage = true;
        boolean deactivateStage = false;
        String msg = " ";

		for(int o = 0; o < SIZE_PROJECT && !isFoundProject; o++){
			if(projects[o].getName().equals(projectName)){
				Stages[] stages = projects[o].getStages();
				isFoundProject = true;
				for(int i = 0; i < stages.length && !foundStage; i++){
					if(i < stages.length && stages[i] != null && stages[i].getActive() == true){
						stages[i].setActive(deactivateStage);
                        stages[i].setRealEndDate(endDate);
                        foundStage = true;
					}
					if(i < stages.length - 1){

                        stages[i+1].setActive(activeStage);
                        msg = "The current stage has been deactivated. The next stage has been activated. ";

						
					}
				}
			}
            
		}
        return msg;


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

  
   /**
    * The function returns a string message displaying the count of different types of capsules.
    * 
    * @return The method `showCount1()` returns a string message that displays the count of four
    * different types of capsules: technic, management, domain, and experience. The message includes
    * the count of each type of capsule.
    */
    public String showCount1(){
        String msg = "The The amount of technic capsules is :" + technicCounter + "The amount of management capsules is :" + managementCounter + "The amount of domain capsules is :" + domainCounter + "The amount of experience capsules is :" + experienceCounter;

        return msg;
    }


    /**
     * This function returns a string containing the lessons learned for a specific stage of a project.
     * 
     * @param projectName A String representing the name of the project for which the learned lessons
     * are to be shown.
     * @param positionStage The position or index of the stage within the project's array of stages.
     * @return The method returns a String containing the lessons learned for all capsules in the
     * specified stage of the specified project, or a message indicating that no capsules or projects
     * have been registered yet.
     */
    public String showLearnedLessonsByStage(String projectName, int positionStage){

        String msg = " No projects Registered yet";
        boolean foundProject = true;

        for(int i = 0; i < SIZE_PROJECT && !foundProject; i ++){
            if(projects[i] != null){
                if(projects[i].getName().equals(projectName)){
                    Stages[] stages = projects[i].getStages();
                    foundProject = false; 
                    Capsule[] capsules = stages[positionStage].getCapsules();
                    for(int x = 0; x < capsules.length ; x++){
                        if(capsules[x] != null){
                            msg += capsules[x].getLessonLearned() + "\n";
                        }
                        else{
                            msg = "No capsules registered yet. ";
                        }
                    }
                }
            }
            
        }
        return msg;
    }

    /**
     * This function returns a message indicating the project with the highest number of capsules among
     * all registered projects.
     * 
     * @return The method returns a String message indicating the project with the most capsules and
     * the number of capsules it has, or a message indicating that no projects are registered yet.
     */
    public String getProjectWithMostUnits(){
        String msg = " ";
        int max = 0;

        for(int i = 0; i < projects.length ; i ++){
            if(projects[i] != null){
                if(projects[i].getAmountOfCapsules() > max){
                    max = projects[i].getAmountOfCapsules();
                    msg = "The project with most capsules is"  + projects[i].getName() + ", with " + max + "capsules. ";
                }

            }
            else{
                msg = "No projects registered yet. ";
            }
        }
        return msg;
    }
    /**
     * The function returns a message indicating whether a worker has registered a capsule in any of
     * the projects and stages.
     * 
     * @param workerName A String representing the name of the worker whose capsules need to be
     * searched for.
     * @return The method returns a message indicating whether a worker has registered a capsule or
     * not. The message can be "Yes, the worker: [workerName], has registered a capsule.", "No, the
     * worker has not registered a capsule.", or "No capsule registered yet." depending on the
     * conditions met in the loops. If there is no project registered yet, the message will be "There
     * is no project registered
     */
    public String getCapsulesByWorker(String workerName){

        String msg = " ";

        for(int i = 0; i < SIZE_PROJECT; i++){
            if(projects[i] != null){
                for(int x = 0; x < projects[i].getStages().length; x++){
                    if(projects[i].getStages()[x] != null){
                        for(int z = 0; z < projects[i].getStages()[x].getCapsules().length; z++){
                            if(projects[i].getStages()[x].getCapsules()[z] != null){
                                if(projects[i].getStages()[x].getCapsules()[z].getEmployeeName().equalsIgnoreCase(workerName)){
                                    msg = "Yes, the worker: " + workerName + ", has registered a capsule.";
                                }
                                else{
                                    msg = "No, the worker has not registered a capsule. ";
                                }

                            }
                            else{
                                msg = "No capsule registered yet. ";
                            }
                        }
                    }
                }
                

            }
            else{
                msg = "There is no project registered yet.";
            }
        }
        return msg;


    }
  

   /**
    * This function sets the start date of a project stage based on the end date of the previous stage.
    * 
    * @param projectName A String representing the name of the project for which the start date needs
    * to be set.
    */
    public void setStartDate(String projectName){
        boolean foundProject = false;
		boolean foundStage = false;

		for(int i = 0; i < SIZE_PROJECT && !foundProject; i++){
			if(projects[i] != null && projects[i].getName().equalsIgnoreCase(projectName)){
				Stages[] stages = projects[i].getStages();
				for(int j = 0; j < stages.length && !foundStage; j++){
					if(i < stages.length && stages[i].getActive() == true){
						foundStage = true;
						Calendar newStartDate = stages[j-1].getRealEndDate();
						stages[j].setRealStartDate(newStartDate);
						stages[j].setBeginDate(newStartDate);
					}
				}

			}
		}

    }
/**
 * This function sets the end date of an active stage in a project based on the project name and the
 * amount of months to add.
 * 
 * @param projectName A String representing the name of the project for which the end date needs to be
 * set.
 * @param amountMonths The number of months to add to the end date of the active stage in the specified
 * project.
 */

    public void setEndDate(String projectName,int amountMonths){

        boolean foundProject = false;
		boolean foundStage = false;

		for(int i = 0; i < SIZE_PROJECT && !foundProject; i++){
			if(projects[i] != null && projects[i].getName().equalsIgnoreCase(projectName)){
				Stages[] stages = projects[i].getStages();
				for(int j = 0; j < stages.length && !foundStage; j++){
					if(i < stages.length && stages[i].getActive() == true){
						foundStage = true;
						stages[i].setEndDate(amountMonths);
					}
				}

			}
		}
        
    }

   /**
    * This Java function returns a string of lessons that contain a specific hashtag by iterating
    * through all projects, stages, and capsules.
    * 
    * @param hashtag A String representing the hashtag to search for in the lessons.
    * @return The method is returning a String that contains all the lessons that have the specified
    * hashtag.
    */
    public String getLessonsWithHashtag(String hashtag){
        String msg = "No projects found. ";

        for(int i = 0; i < SIZE_PROJECT; i++){
            if(projects[i] != null){
                Stages[] stages = projects[i].getStages();
                for(int x = 0; x++ < stages.length; x++){
                    Capsule[] capsules = stages[x].getCapsules();
                    for(int y = 0; y < capsules.length; y++){
                        if(capsules[y] != null && capsules[y].getApprove() == true && capsules[y].getPublished() == true){
							msg += capsules[y].getLessonsWithHashtag(hashtag);
						}
                    }
                }
            }
        }
        return msg;
    }

    
    
    

   
}
