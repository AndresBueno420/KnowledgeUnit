package model;

public class Controller {

    public static final int SIZE_PROJECT = 10;
    private Project[] projects;
    

    public Controller(){
        projects = new Project[SIZE_PROJECT];
        
    }
    public void addProject(String projectName, String clientName,String initialDate, String endDate, double budget, String manager, String managerPhone, String clientPhone){
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

    
}
