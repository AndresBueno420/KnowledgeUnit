package model;
import java.util.Calendar;


public class Project {
    private static final int SIZE = 10;
    private String projectName;
    private String clientName;
    private Calendar initialDate;
    private Calendar endDate;
    private double budget;
    private String manager;
    private String managerPhone;
    private String clientPhone;
    private Stages[] stages;
    
   

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
    
}
