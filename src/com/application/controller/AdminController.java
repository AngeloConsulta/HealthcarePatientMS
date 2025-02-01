/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controller;


//import com.application.model.User;
//import com.application.view.UserView;

/**
 *
 * @author Administrator
 */
import com.application.Application;
import com.application.dao.AdminDAO;
import com.application.model.Admin;
import com.application.model.Doctor;
import com.application.model.Patient;
import com.application.view.AdminView;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class AdminController {
    private final Application app = new Application();
    private final AdminView adminView = new AdminView();
    private final AdminDAO adminDAO = new AdminDAO();
    private final ArrayList<Admin> admins = new ArrayList<>();
    private final ArrayList<Patient> patients = new ArrayList<>();
    private final ArrayList<Doctor> doctors = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private Admin loggedInAdmin = null;
    
    
    public void handleAdminFlow(){
        System.out.println("\nAdmin Dashboard System");
        System.out.println("[1]  Register");
        System.out.println("[2]  Login");
        System.out.println("[3]  Back to Main Menu");
        System.out.print("\nEnter your choice: ");
//        int choice = sc.nextInt();
        
        try{
            int choice =sc.nextInt();
            switch(choice){
                case 1 :
                    registerAdmin();
                    break;
                case 2 :
                    loginAdmin();
                    break;
                case 3:
                    app.mainMenu();
                default :
                    adminView.displayMessage("Invalid choice, Please try again" );
                    handleAdminFlow();
            }
            
        }catch(Exception e){
             System.out.println("Invalid Input, Please input number only");
             app.mainMenu();
        } 
    }
    public void registerAdmin(){
        Admin admin = adminView.getAdminDetails();
        boolean isRegistered = adminDAO.createAccount(admin);
        
        if(isRegistered){
            adminView.displayMessage("Admin Registered Successfully");
            handleAdminFlow();
        }else{
            adminView.displayMessage("Registration Failed. Try again");
             handleAdminFlow();
        }
    }
    public void loginAdmin(){ ///Logging In of Admin
        Admin admin =adminView.getLoginDetails();
        boolean isAuthenticated = adminDAO.verifyCredentials(admin);
        
        if(isAuthenticated){
            adminView.displayMessage("Login Successful");
            handleDashboard();
        }else{
            adminView.displayMessage("Invalid Credentials. Try Again.");
            handleAdminFlow();
        }
    }
    public void handleDashboard(){
        while(true){
     
            int choice = adminView.getAdminDashboardChoice();
            switch(choice){
                case 1:
                    handlePatientManagement();
                    //Call the PatientController or related Functionality
                    break;
                case 2:
                    handleDoctorManagement();
                    //call the doctorController or related functionality
                    break;
                case 3:
                    searchPerson();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    adminView.displayMessage("Invalid Choice. Try Again");
                    handleDashboard(); 
            }
        }
    }
    public void searchPerson(){
        sc.nextLine();
        System.out.print("Enter the Name or Username to Search: ");
        String keyword = sc.nextLine().trim();
        if(keyword.isEmpty()){
            System.out.println("Please enter a valid search keyword");
            return;
        }
        List<Object[]> results = adminDAO.searchPerson(keyword);
        adminView.displaySearchResults(results);
    }
    private void  handlePatientManagement(){
        PatientController patientController = new PatientController();
        patientController.managePatients();
    }
    private void  handleDoctorManagement(){
        DoctorController docController = new DoctorController();
        docController.manageDoctors();
    }
            
//     public void dashboardchoice(){
//        switch(){
//            case 1 ->registerAdmin();
//            case 2 ->loginAdmin();
//            case 3 -> app.mainMenu();
//            default ->adminView.displayMessage("Invalid choice, Please try again");
//        }  
//    }

 
   
//    public void displayDashboard(){
//        while(true){
//            view.displayDashboard();
//            int choice = Integer.parseInt(view.getInput());
//        
//        switch(choice){
//            case 1:
//                view.displayMessage("View an appointment..");
////                bookAppointment();
//                break;
//            case 2:
//                view.displayMessage("Create Patient Information..");
////                viewAppointment();
//                break;
//            case 3:
//                view.displayMessage("Update Patient Information..");
////                cancelAppointment();
//                break;
//            case 4:
//                view.displayMessage("Delete Patient Information..");
//                break;
//            case 5:
//                view.displayMessage("Logging out..");
//                break;
//            default:
//                view.displayMessage("Invalid option. Try Again");
//                displayDashboard();
//            
//        }
//     
//        }
//    }
}
