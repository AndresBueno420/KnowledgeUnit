package ui;
import java.util.Scanner;
import model.Controller;

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

    public void executeOption(int option){
        switch (option) {
            case 1:
                 
                System.out.println(controller);
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

    
}
