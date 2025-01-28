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
    private final AdminController admControl = new AdminController();
        
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
                    admControl.handleDashboard();
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
        // Display existing patients
        List<Patient> patients = ptDAO.getAllPatient();
         ptview.displayPatients(patients);

        // Get the patient ID to update
        int patientId =  ptview.getPatientIdInput("Enter the ID of the patient to update: ");

        // Fetch the patient to update
        Patient patient = patients.stream()
                              .filter(p -> p.getId() == patientId)
                              .findFirst()
                              .orElse(null);
        if (patient == null) {
            ptview.displayMessage("Patient with ID " + patientId + " not found!");
            return;
        }

         // Get updated details from the user
        ptview.displayMessage("Enter new details for the patient (leave blank to keep current values):");
        patient.setFull_name(ptview.getInputOrDefault("Name", patient.getFull_name()));
        patient.setContact_number(ptview.getInputOrDefault("Contact Number", patient.getContact_number()));
        patient.setGender( ptview.getInputOrDefault("Gender", patient.getGender()));
        patient.setDob(ptview.getDateInputOrDefault("Date of Birth: (YYYY-MM-DD)", patient.getDob()));
        patient.setAddress( ptview.getInputOrDefault("Address", patient.getAddress()));
        

    // Update the patient in the database
        boolean success = ptDAO.updatePatient(patient);
        if (success) {
            ptview.displayMessage("Patient updated successfully!");
        } else {
            ptview.displayMessage("Failed to update the patient. Please try again.");
        }

    }
    private void deletePatient(){
        
    }
    
    public void handlePatientFlow(){
        while(true){
            try{
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
            }catch(Exception e){
                System.out.println("Invalid Input, Please input number only");
                app.mainMenu();
            }
        }
    }
    //This is only accessed by the patient user
    public void login(){
        Patient login = ptview.displayLoginPrompt();
        boolean isAuthenticated = ptDAO.verifyCredentials(login);
        if(isAuthenticated){
            ptview.displayMessage("\nLogin Successfully! Welcome, "+ login.getUsername());
            displayDashboard();
            
        }else{
            ptview.displayMessage("Invalid Credentials. Please try Again");
            handlePatientFlow();
        }
        
    }

    public void displayDashboard(){
        while(true){
            try{
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
            }catch(Exception e){
                
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
