/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controller;

import com.application.Application;
import com.application.dao.DoctorDAO;
import com.application.dao.PatientDAO;
import com.application.model.Doctor;
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
    private final DoctorController Doctrol = new DoctorController();
    private final AdminController admControl = new AdminController();
    private Patient loggedInPatient;
    
    
    
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
                    deleteChoice();
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
        List<Patient> patient = ptDAO.getAllActivePatients();
        if(patient.isEmpty()){
            ptview.displayMessage("No patients found");     
        }else{
            ptview.displayPatients(patient);
        }
    }
    private void updatePatient(){
        // Display existing patients
        List<Patient> patients = ptDAO.getAllActivePatients();
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
    public void deleteChoice(){
        while (true){
             try{
                int choice = ptview.displayDelChoice();
                switch(choice){
                case 1:
                    archiveDeletePatient();
                    break;
                case 2:
                    restoreArchivePatient();
                    break;
                case 3:
                    permanentDelete();
                    break;
                case 4:
                    managePatients();
                    break;
                default:
                    ptview.displayMessage("Invalid Choice. Please Try again");
                    managePatients();
                }
            }catch(Exception e){
                 System.out.println(e);
                 managePatients();
             }
           
        }
    }
    public void archiveDeletePatient(){
        viewAllPatients();
        int patient_id =ptview.getPatientIdInp("Enter the ID of the patient to archive: ");
        boolean success =  ptDAO.softdDeletePatient(patient_id);
        ptview.displayMessage(success ? "Patient archived successfully! ": "Failed to archived patient.");
    }
    public void restoreArchivePatient(){
       List<Patient> archivePatients = ptDAO.getArchivedPatients();
       ptview.displayPatients(archivePatients);
       
       int choice = ptview.getArchiveRestoreChoice();
       if(choice ==1){
           int patient_id = ptview.getPatientIdInp("Enter the ID of patient to restore: ");
           boolean success = ptDAO.restorePatient(patient_id);
           ptview.displayMessage(success ? "Patient restored successfully! ":"Failed to restore patient");
       }
    }
    public void permanentDelete(){
        viewAllPatients();
        int patient_id = ptview.getPatientIdInp("Enter the ID of the patient to archive: ");
        boolean success = ptDAO.hardDeletePatient(patient_id);
        ptview.displayMessage(success? "Patient record permanently deleted": "Failed to permanently delete");
    }
    //This is for Patient Access
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
                    app.mainMenu();
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
        Patient patient = ptDAO.getPatientLogin(login.getUsername(), login.getPassword());
//        boolean isAuthenticated = ptDAO.verifyCredentials(login);
        if(patient != null ){
            ptview.displayMessage("\nLogin Successfully! Welcome, "+ patient.getUsername());
            displayDashboard(patient);
            
        }else{
            ptview.displayMessage("Invalid Credentials. Please try Again");
            handlePatientFlow();
        }
        
    }

    public void displayDashboard(Patient patient){
        while(true){
            try{
                int choice = ptview.displayDashboard();
            switch(choice){
                case 1:
                ptview.displayMessage("\n============ Patient Personal Details ============");
                viewPersonalDetails(patient);
                break;
            case 2:
                ptview.displayMessage("\n ======= Update Patient Personal Information =======");
                updatePatientInfo(patient);
                break;
            case 3:
                ptview.displayMessage("======= Viewing Available Doctor =======");
                viewAvailableDoctors();
                break;
            case 4:
                ptview.displayMessage("===== You are booking an appointment ");
//                bookAppointment();
            case 5:
                System.exit(0);
                break;
                        
            case 6:
                ptview.displayMessage("Logging out..");
                System.exit(0);           
            default:
                ptview.displayMessage("Invalid option. Try Again");
                displayDashboard(patient);
            
            }
            }catch(Exception e){
                
            }
            
        }
    }
    private void viewPersonalDetails(Patient patient){
       ptview.displayPatientDetails(patient);
    }
    
    
    private void updatePatientInfo(Patient patient){
     ptview.displayPatientDetails(patient);
    // Ask for new input, keeping old values if blank
    ptview.displayMessage("\nEnter new details for the patient (leave blank to keep current values):");
    String newContact = ptview.getInputOrDefault("Contact Number", patient.getContact_number());
    String newAddress = ptview.getInputOrDefault("Address", patient.getAddress());
    String newEmergencyName = ptview.getInputOrDefault("Emergency Contact Name", patient.getEmergency_contact_name());
    String newEmergencyNo = ptview.getInputOrDefault("Emergency Contact Number", patient.getEmergency_contact_number());
    String newBloodType = ptview.getInputOrDefault("Blood Type", patient.getBlood_type());
    String newMedicalConditions = ptview.getInputOrDefault("Medical Conditions", patient.getMedical_conditions());
    String newMedications = ptview.getInputOrDefault("Medications", patient.getMedications());
    String newAllergies = ptview.getInputOrDefault("Allergies", patient.getAllergies());
    
    // Set updated values
    patient.setAddress(newAddress);
    patient.setContact_number(newContact);
    patient.setEmergency_contact_name(newEmergencyName);
    patient.setEmergency_contact_number(newEmergencyNo);
    patient.setBlood_type(newBloodType);
    patient.setMedical_conditions(newMedicalConditions);
    patient.setMedications(newMedications);
    patient.setAllergies(newAllergies);
    
    // Update the patient info in the database
    boolean success = ptDAO.updatePatientPersonalDetails(patient);
    if (success) {
        ptview.displayMessage("Personal information updated successfully!");
    } else {
        ptview.displayMessage("Failed to update personal information. Please try again.");
    }
    }
    
    
    public void viewAvailableDoctors() {
        Doctrol.viewAllDoctorByPatient();
    }
//    public void bookAppointment(){
//        int doctorId = ptview.getDoctorIdForAppointment();
//        String date = ptview.getAppointmentdate();
//        String reason = ptview.getAppointmentReason();
//        boolean success = ptDAO.bookAppointment(loggedInPatient.getId(), doctorId, date, reason);
//    
//        if (success){
//            ptview.displayMessage("Appointment book successfully! ");
//    
//        }else{
//            ptview.displayMessage("Failed to book appointment");
//        }
//    }
    

}
