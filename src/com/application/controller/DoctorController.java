/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controller;

import com.application.Application;
import com.application.dao.DoctorDAO;
import com.application.model.Doctor;
import com.application.view.DoctorView;
import java.util.List;



/**
 *
 * @author Administrator
 */
public class DoctorController {
    private final DoctorView docview = new DoctorView();
    private final DoctorDAO docDAO = new DoctorDAO();
    private final Application app = new Application();
    public void manageDoctors(){
         while(true){
            int choice = docview.getDoctorManagementChoice();
            switch (choice){
                case 1:
                    addDoctor();
                    break;
                case 2:
                    viewAllDoctor();
                    break;
                case 3:
                    updateDoctor();
                    break;
                case 4:
                    deleteDoctor();
                    break;
                case 5:
                    return;
                default:
                    docview.displayMessage("Invalid Choice");
            }
        }
    }
    private void addDoctor(){
        Doctor doctor = docview.getDoctorDetails();
        boolean isAdded = docDAO.createAccount(doctor);
        if(isAdded){
            docview.displayMessage("Doctor Added Successfully");
        }else{
            docview.displayMessage("Failed to add Doctor. Please try again");
        }
    }
    private void viewAllDoctor(){
        List<Doctor> doc = docDAO.getAllDoctors();
        docview.displayDoctorTable(doc);
    }
    //Soft delete
    private void updateDoctor(){
        
    }
    //Hard delete
    private void deleteDoctor(){
        
    }
     public void handleDoctorFlow(){
        while(true){
            int choice = docview.handleLoginDoctor();
            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    app.mainMenu();
                    break;
                default:
                    docview.displayMessage("Invalid Choice");
                
            }
        }
    }
            
      

    
    
   
    public void login(){
        
    }
//    public void createAccount(){
//        view.displayMessage("Enter Username: ");
//        String username = view.getInput();
//        view.displayMessage("Enter Password: ");
//        String password = view.getInput();
//        view.displayMessage("Enter name: ");
//        String name = view.getInput();
//        view.displayMessage("Enter age: ");
//        int age = Integer.parseInt(view.getInput());
//        view.displayMessage("Enter Gender: ");
//        String gender = view.getInput();
//        view.displayMessage("Enter License Number: ");
//        String licNum = view.getInput();
//        
//        if(model.createAccount(username, password, name, age, gender,licNum)){
//            view.displayMessage("Account created successfully. Please log in");
//        }else{
//            view.displayMessage("Failed to create acount. Please try again");
//        }
//    }
//    
//    public void displayDashboard(){
//        while(true){
//            view.displayDashboard();
//            int choice = Integer.parseInt(view.getInput());
//        
//        switch(choice){
//            case 1:
//                view.displayMessage("Booking an appointment..");
////                bookAppointment();
//                break;
//            case 2:
//                view.displayMessage("Viewing upcoming appointments..");
////                viewAppointment();
//                break;
//            case 3:
//                view.displayMessage("Canceling an appoitnment..");
////                cancelAppointment();
//                break;
//            case 4:
//                view.displayMessage("Logging out..");
//            default:
//                view.displayMessage("Invalid option. Try Again");
//                displayDashboard();
//            
//        }
//        }
//    }
//    
    
}
