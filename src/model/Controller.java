package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller {

    public static final int SIZE_PROJECT = 10;
    private Project[] projects;
    

    public Controller(){
        projects = new Project[SIZE_PROJECT];
        
    }
    public void addProject(String projectName, String clientName,Calendar initialDate, Calendar endDate, double budget, String manager, String managerPhone, String clientPhone){
        Project project = new Project(projectName, clientName, initialDate, endDate, budget, manager, managerPhone, clientPhone);
        int pos = getFirstValidPosition();
        if(pos != -1){
            projects[pos] = project;
        }
    }
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
		}


	}
    
}
