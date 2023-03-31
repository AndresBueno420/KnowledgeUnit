package model;

public class Project {
    private String projectName;
    private String clientName;
    private String initialDate;
    private String endDate;
    private double budget;
    private String manager;
    private String managerPhone;
    private String clientPhone;

    public Project(String projectName, String clientName,String initialDate, String endDate, double budget, String manager, String managerPhone, String clientPhone){
        this.projectName = projectName;
        this.clientName = clientName;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.budget = budget;
        this.manager = manager;
        this.managerPhone = managerPhone;
        this.clientPhone = clientPhone;

    }
    
}
