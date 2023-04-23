package ui;
import java.util.Scanner;
import model.Controller;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Main {
    private Controller controller;
    private Scanner reader;

    public Main(){
        this.reader = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args)throws Exception{

        Main view = new Main();

        int option = 0;

        do{
            view.menu();
            option = view.validateIntegerInput();
            view.executeOption(option);



        }while(option != 10);

        System.out.println("Thanks for using the system. ");


    }
    public void menu(){
        System.out.println(" ---------- GreenSQA System ----------");
        System.out.println("1. Create a project.");
        System.out.println("2. End stage of a project");
        System.out.println("3. Register knowledge capsule.");
        System.out.println("4. Approve capsule.");
        System.out.println("5. Publish capsule .");
        System.out.println("6. Show the amount of capsules for each type.");
        System.out.println("7. Show the lessons learned on a stage of a project. ");
        System.out.println("8. Show the project with most capsules. ");
        System.out.println("9. Search if a worker has registered a capsule. ");
        System.out.println("10. Exit.");
        System.out.println(" ------------------------------------ ");

        
    }
    
    public int validateIntegerInput(){
        int option = 0; 
        if(reader.hasNextInt()){
            option = reader.nextInt(); 
        }
        else{
            reader.nextLine();// limpiar el scanner 
            option = -1; 
            System.out.println("Ingrese un valor entero."); 
        }
        return option; 
    }

    public void executeOption(int option) throws Exception{
        switch (option) {
            case 1:
                addProject();
                registStages();
                break;

            case 2:
                finishStage();
                startDates();
                endDates();
                break;

            case 3: 
                registCapsule();
                break;

            case 4:
                approveCapsule();
                break; 

            case 5:
                publishCapsule();
                break; 
            case 6:
                showCountCapsuleType();
                break;
            case 7:
                showLessonsByStage();
                break;
            case 8:
                showProjectWithMostCapsules();
                break;
            case 9:
                showCapsuleByWorker();

            case -1: 
                System.out.println("Invalid Option!!"); 
                break; 
        }
    }

   // The `addProject()` method is responsible for getting input from the user to create a new project.
   // It prompts the user to enter the project name, client name, client phone number, start date, end
   // date, budget, manager name, and manager phone number. 
   //It then uses a `SimpleDateFormat` object to parse the start and end dates into `Calendar` objects. Finally, it calls the `addProject()`
   // method of the `Controller` class to create the new project with the given information. The method
   // throws an `Exception` if there is an error parsing the dates.
    public void addProject()throws Exception{
        String projectName;
        String clientName;
        String initialDate;
        String endDate;
        double budget;
        String manager;
        String managerPhone;
        String clientPhone;

        System.out.println("Type the name of the project: ");
        projectName = reader.next();
        System.out.println("Type the name of the client: ");
        clientName = reader.next();
        System.out.println("Type the number of the client: ");
        clientPhone = reader.next();
        System.out.println("Type the date of starting: ");
        initialDate = reader.next();
        System.out.println("Type the date of ending: ");
        endDate = reader.next();
        System.out.println("Type the budget for the project: ");
        budget = reader.nextDouble();
        System.out.println("who is the manager of the project: ");
        manager = reader.next();
        System.out.println("Type the number of the manager: ");
        managerPhone = reader.next();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Calendar beginDate = Calendar.getInstance();
        beginDate.setTime(format.parse(initialDate));
        Calendar finishDate = Calendar.getInstance();
        finishDate.setTime(format.parse(endDate));

        controller.addProject(projectName, clientName, beginDate, finishDate, budget, manager, managerPhone, clientPhone);

    }

   /**
    * This function registers stages for a project by taking input from the user for project name,
    * expected start date, and real start date. Initially we take these to date in order operate them 
    */
    public void registStages() throws Exception{

        String projectName = " ";
        String expectedStartDateStageStr = " ";
        String realStartStageDateStr = " ";
        int expectedMonths = 0;
        

        System.out.println("Confirm the name of the project: ");
        projectName = reader.next();
        System.out.println("Type the expected start date for the first stage: ");
        expectedStartDateStageStr = reader.next();
        Calendar expectedStartDateStage = stringsToCalendar(expectedStartDateStageStr);

        System.out.println("Type the real start date for the first stage: ");
        realStartStageDateStr = reader.next();
        Calendar realStartDate = stringsToCalendar(realStartStageDateStr);
        System.out.println("Type the months for the first stage:");
        expectedMonths = reader.nextInt();

        

        controller.initializeStages(projectName, expectedStartDateStage, realStartDate, expectedMonths);

        System.out.println("The project has been registed succesfully.");
    }

  /**
   * This function prompts the user to confirm the name of a project and sets its start date 
   */
    public void startDates(){
        String projectName = "";
        System.out.println("Confirm the name of the project. ");
        projectName = reader.next();
        controller.setStartDate(projectName);
    }
    /**
     * This function prompts the user to confirm and input the project name and duration in months, and
     * then sets the end date for the project using a controller.
     */
    public void endDates(){
        String projectName = "";
        int amountOfMonths = 0;

        System.out.println("Confirm again to set the end dates. ");
        projectName = reader.next();

        System.out.println("Type the amount of months of duration. ");
        amountOfMonths = reader.nextInt();

        controller.setEndDate(projectName, amountOfMonths);
    }

   /**
    * This function finishes the current stage of a project and initiates the next stage by taking
    * input from the user for project name, end date, and estimated amount of months for the next
    * stage.
    */
    public void finishStage() throws Exception{
        
        boolean flag = controller.checkProject();
        String endDateStr = " ";
        int amountMonths = 0;
        String projectName = " ";

        if(flag == true){
            System.out.println("Type the name of the project: ");
            projectName =reader.next();
            System.out.println("The current stage will be finished. Please, type today's date: ");
            endDateStr = reader.next();
            Calendar endDate = stringsToCalendar(endDateStr);
            System.out.println("Type the estimated amount of months for the next stage: ");
            amountMonths = reader.nextInt();
    
            controller.finishStage(projectName, endDate, amountMonths);
    
            System.out.println("The current stage was finished succesfully. The next stage has been initiated.");

        }
        else if(flag == false){
            System.out.println("There is no project created yet");
        }
    
        

    }
    /**
     * This function registers a capsule by taking input from the user and passing it to a controller
     * to add it to a project.
     */
    public void registCapsule(){

        boolean flag = controller.checkProject();
        String id = ""; 
        String description = "";
        String workerName = "";
        String workerCharge = "";
        String lection = "";
        int type;
        String hashtags = " ";
        String projectName = " ";

        if(flag == true){
            System.out.println("Type the name of the project.");
            projectName = reader.next();
            System.out.println("Type the capsule id: "); 
            id = reader.next(); 
            System.out.println("Type a short description: ");
            reader.next(); 
            description = reader.nextLine();
            System.out.println("Type the worker name: ");
            workerName = reader.next();
            System.out.println("Type the worker charge: ");
            workerCharge = reader.next();
            System.out.println("Type the lection to save: ");
            reader.next();
            lection = reader.nextLine(); 
            System.out.println("Type the hashtags asociated: ");
            reader.next();
            hashtags = reader.nextLine();

            System.out.println("Choose the type of capsule :"); 
            System.out.println(" 1. for Technic");
            System.out.println(" 2. for Management. ");
            System.out.println(" 3. for Domain. ");
            System.out.println(" 4. for Experience. ");
            type = reader.nextInt();

        controller.addCapsule(projectName, id, type, description, workerName, workerCharge,lection,hashtags);

        System.out.println("The capsule has been registed.");
            

        }
        else if(flag == false){
            System.out.println("There is no project created yet");
        }
    

    }
   /**
    * This function prompts the user to input information about a capsule and then calls a controller
    * method to approve it.
    */
    public void approveCapsule() throws Exception{

        boolean flag = controller.checkProject();
        boolean newStatus = true;
        String capsuleId = " ";
        String publishDateStr = " ";
        String projectName = " ";

        if(flag == true){
            System.out.println("Type the name of the project: ");
            projectName = reader.next();
            System.out.println("Please, type the id of the capsule to approve:  ");
            capsuleId = reader.next();
            System.out.println("Please, type today's date: ");
            publishDateStr = reader.next();
            Calendar publishDate = stringsToCalendar(publishDateStr);
    
            String msgConfirm = controller.approveCapsule(projectName, capsuleId, publishDate, newStatus);
    
            System.out.println(msgConfirm);
        }
        else if(flag == false){
            System.out.println("There is no project created yet");
        }
    }

 /**
  * This function prompts the user to input a project name and capsule ID, and then calls a controller
  * method to publish the capsule with the given parameters.
  */
    public void publishCapsule(){

        boolean flag = controller.checkProject();
        boolean publishStatus = true;
        String capsuleId = " ";
        String projectName = " ";

        if(flag == true){
            System.out.println("Please, type the name of the project: ");
            projectName = reader.next();
            System.out.println("Please, type the id of the capsule to publish: ");
            capsuleId = reader.next();
        

            String url = controller.publishCapsule(projectName, capsuleId, publishStatus);

            System.out.println(url);

        }
        else if(flag == false){
            System.out.println("There is no project created yet");
        }
       
    }

 /**
  * The function converts a string date in the format "dd/MM/yyyy" to a Calendar object.
  * 
  * @param date The date parameter is a string representing a date in the format "dd/MM/yyyy".
  * @return A Calendar object is being returned.
  */
    public Calendar stringsToCalendar(String date) throws Exception{
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Calendar newDate = Calendar.getInstance();
        newDate.setTime(formatter.parse(date));

        return newDate; 
    }

    /**
     * This function checks if a project exists and shows the count of a specific type of capsule if it
     * does, otherwise it prints a message saying there is no project created yet.
     */
    public void showCountCapsuleType(){
        boolean flag = controller.checkProject();
        
        if( flag == true){
            String msg = controller.showCount1();
            System.out.println(msg);
        }
        else if(flag == false){
            System.out.println("There is no project created yet");
        }
    }

    /**
     * This function prompts the user to input a project name and select a stage, then calls a
     * controller method to display learned lessons for that project and stage.
     */
    public void showLessonsByStage(){

        String projectName = " ";
        int stagePosition = 0;

        System.out.println("Type the name of the project: ");
        projectName = reader.next();

        System.out.println("Select the stage: ");
        System.out.println("1. Start . ");
        System.out.println("2. Analisys. ");
        System.out.println("3. Design. ");
        System.out.println("4. Execution. ");
        System.out.println( "5. Close.");
        System.out.println("6. Follow up and control. ");
        stagePosition = reader.nextInt();

        String msg = controller.showLearnedLessonsByStage(projectName, stagePosition);
        System.out.println(msg);
    }

    public void showProjectWithMostCapsules(){

        String msg = controller.getProjectWithMostUnits();
        System.out.println(msg);

    }
   /**
    * This function prompts the user to input an employee name, retrieves a message containing
    * capsules associated with that employee from a controller, and prints the message to the console.
    */
    public void showCapsuleByWorker(){

        String workerName;

        System.out.println("Type the name of the employee. ");
        workerName = reader.next();

        String msg = controller.getCapsulesByWorker(workerName);
        System.out.println(msg);
    }




    
}
