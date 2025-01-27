/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controller;

import com.application.Application;
import com.application.dao.PatientDAO;
import com.application.model.Patient;
import com.application.view.PatientView;
import java.util.List;

/**
 *
 * @author Administrator
 */


public class PatientController {
   
    private final PatientView ptview = new PatientView();
    private final PatientDAO ptDAO = new PatientDAO();
    private final Application app = new Application();
   
        
    public void managePatients(){
        while(true){
            int choice = ptview.getPatientManagementChoice(); // handle the option of CRUD Operation in Patient
            switch (choice){
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    return;
                default:
                    ptview.displayMessage("Invalid Choice");
            }
        }
    }
    private void addPatient(){
        Patient patient = ptview.getPatientDetails();
        boolean isAdded = ptDAO.createAccount(patient);
        if (isAdded){
            ptview.displayMessage("\nPatient added Successfully");
        }else{
            ptview.displayMessage("\nFailed to add Patient, Try Again");
        }
    }
    private void viewAllPatients(){
        List<Patient> patient = ptDAO.getAllPatient();
        if(patient.isEmpty()){
            ptview.displayMessage("No patients found");     
        }else{
            ptview.displayPatients(patient);
        }
        int choice = ptview.displayBackOption();
        //if the user chooses to go back
        if(choice == 1){        
        }else{
            ptview.displayMessage("Invalid Choice, Please Try Again");
        }
    }
    private void updatePatient(){
        
    }
    
    private void deletePatient(){
        
    }
    public void handlePatientFlow(){
        while(true){
            int choice = ptview.handleLoginPatient();
            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    app.mainMenu();
                    break;
                default:
                    ptview.displayMessage("Invalid Choice");
                
            }
        }
    }
    public void login(){
        Patient login = ptview.displayLoginPrompt();
        boolean isAuthenticated = ptDAO.verifyCredentials(login);
        if(isAuthenticated){
            ptview.displayMessage("\nLogin Successfully! Welcome, "+ login.getUsername());
            displayDashboard();
            
        }else{
            ptview.displayMessage("Invalid Credentials. Please try Again");
        }
        
    }

    public void displayDashboard(){
        while(true){
            int choice = ptview.displayDashboard();
            switch(choice){
                case 1:
                ptview.displayMessage("Booking an appointment..");
                
                break;
            case 2:
                ptview.displayMessage("Viewing upcoming appointments..");
                
                break;
            case 3:
                ptview.displayMessage("Canceling an appoitnment..");
               
                break;
            case 4:
                ptview.displayMessage("Logging out..");
                System.exit(0);
            default:
                ptview.displayMessage("Invalid option. Try Again");
                displayDashboard();
            
            }
        }
    }

    
    
    
//    private void bookAppointment(){
//        view.displayMessage("Booking appointment..");
//        
//    }
//    private void viewAppointment(){
//        view.displayMessage("Viewing appointments...");
//        
//    }
//    private void cancelAppointment(){
//        view.displayMessage("Canceling Appointment...");
//    }
//    
//    private int getUserInput(){
//        return 1;
//    }
}
