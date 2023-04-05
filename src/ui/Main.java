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

    public static void main(String[] args){

        Main view = new Main();

        int option = 0;

        do{
            view.menu();



        }while(option != 6);

        System.out.println("Thanks for using the system. ");


    }
    public void menu(){
        System.out.println("1. Create a project.");
        System.out.println("2. End stage of a project");
        System.out.println("3. Register knowledge capsule.");
        System.out.println("4. Approve capsule.");
        System.out.println("5. Publish capsule .");
        System.out.println("6. Exit.");

        
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
                break;

            case 2:
                
                break;

            case 3: 
                
                break;

            case 6:
                System.out.println("Exit."); 
                break; 

            case -1: 
                System.out.println("Invalid Option!!"); 
                break; 
        }
    }

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

    
}
