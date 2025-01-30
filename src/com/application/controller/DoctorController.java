/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controller;

import com.application.Application;
import com.application.dao.DoctorDAO;
import com.application.model.Doctor;
import com.application.view.DoctorView;
import com.application.controller.AdminController;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Administrator
 */
public class DoctorController {
    private final DoctorView docview = new DoctorView();
    private final DoctorDAO docDAO = new DoctorDAO();
    private final Application app = new Application();
    private final AdminController admControl = new AdminController();
    
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
                    deleteDocChoice();
                    break;
                case 5:
                    admControl.handleDashboard();
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
        List<Doctor> doc = docDAO.getAllActiveDoctor();
        docview.displayDoctorTable(doc);
    }
    //This is for the Access of Doctor
    public void viewAllDoctorByPatient(){
        List<Doctor> doc = docDAO.getAllActiveDoctor();
        docview.viewAllDoctorForPatient(doc);
    }
    //Update CRUD API
    private void updateDoctor(){
    // Display existing doctors
    List<Doctor> doctors = docDAO.getAllActiveDoctor();
    docview.displayDoctorTable(doctors);
    
    // Get the doctor ID to update
    int doctorId = docview.getDoctorIdInput("Enter the ID of the doctor to update: ");
    
    // Fetch the doctor to update
    Doctor doctor = doctors.stream()
                           .filter(d -> d.getId() == doctorId)
                           .findFirst()
                           .orElse(null);
    if (doctor == null) {
        docview.displayMessage("Doctor with ID " + doctorId + " not found!");
        return;
    }
    
    // Get updated details from the user
    docview.displayMessage("Enter new details for the doctor (leave blank to keep current values):");
    String newUsername =docview.getInputOrDefault("Username", doctor.getUsername());
    String newName = docview.getInputOrDefault("Name", doctor.getName());
    String newSpecialization = docview.getInputOrDefault("Specialization", doctor.getSpecialization());
    String newLicenseNumber=docview.getInputOrDefault("License Number", doctor.getLicenseNumber());
    String newContactNumber = docview.getInputOrDefault("Contact Number", doctor.getContactNumber());
    String newGender = docview.getInputOrDefault("Gender", doctor.getGender());
    LocalDate newDOB = docview.getDateInputOrDefault("DOB (YYYY-MM-DD)", doctor.getDOB());
    String newAddress = docview.getInputOrDefault("Address", doctor.getAddress());
    LocalDate newAvaildate = docview.getDateInputOrDefault("Availability Status (YYYY-MM-DD)", doctor.getAvailabilityStatus());
    
    //Set the Updated Values
     doctor.setUsername( newUsername);
     doctor.setName(newName);
     doctor.setSpecialization(newSpecialization);
     doctor.setLicenseNumber(newLicenseNumber);
     doctor.setContactNumber( newContactNumber);
     doctor.setGender(newGender);
     doctor.setDOB(newDOB );
     doctor.setAddress(newAddress);
     doctor.setAvailabilityStatus( newAvaildate );
    // Update the doctor in the database
    boolean success = docDAO.updateDoctor(doctor);
    if (success) {
        docview.displayMessage("Doctor updated successfully!");
        viewAllDoctor();
    } else {
        docview.displayMessage("Failed to update the doctor. Please try again.");
    }
    }
    //Choice for Soft delete and hard delete
    private void deleteDocChoice(){
        while (true){
            int choice = docview.displayDocDelChoice();
            switch(choice){
                case 1:
                    archiveDeleteDoctor();
                    break;
                case 2:
                    restoreArchiveDoctor();
                    break;
                case 3:
                    permanentlyDelete();
                    break;
                case 4:
                    manageDoctors();
                    break;
                default:
                    docview.displayMessage("Invalid Choice. Please Try again");
                    manageDoctors();
                    
            }
        }
    }
     public void archiveDeleteDoctor(){
        viewAllDoctor();
        int id = docview.getDoctorIdInp("Enter the ID of the patient to archive: ");
        boolean success =  docDAO.softdDeletePatient(id);
        docview.displayMessage(success ? "Patient archived successfully! ": "Failed to archived patient.");
    }
    public void restoreArchiveDoctor(){
       List<Doctor> archiveDoctor = docDAO.getArchivedDoctor();
       docview.displayDoctorTable(archiveDoctor);
       
       int choice = docview.getRestoreChoice();
       if(choice ==1){
           int patient_id = docview.getDoctorIdInp("Enter the ID of patient to restore: ");
           boolean success = docDAO.restorePatient(patient_id);
           docview.displayMessage(success ? "Patient restored successfully! ":"Failed to restore patient");
       }
    }
    public void permanentlyDelete(){
        viewAllDoctor();
        int patient_id = docview.getDoctorIdInp("Enter the ID of the patient to archive: ");
        boolean success = docDAO.hardDeletePatient(patient_id);
        docview.displayMessage(success? "Patient record permanently deleted": "Failed to permanently delete");
    }
    
        
    
    //This Line of Code is Only for Access of Doctors
     public void handleDoctorFlow(){
        while(true){
            try{
                int choice = docview.handleLoginDoctor();
            switch(choice){
                case 1:
                    loginDoctor();
                    break;
                case 2:
                    app.mainMenu();
                    break;
                default:
                    docview.displayMessage("Invalid Choice");
                    continue;
            }
            }catch(Exception e){
                System.out.println("Invalid Input, Please input number only");
                continue;
            }
            
        }
    }
     
    public void loginDoctor(){
        Doctor doc =docview.displayLoginPrompt();
        Doctor doctor =docDAO.getDoctorLogin(doc.getUsername(), doc.getPassword());
        if (doctor != null){
            docview.displayMessage("\nLogin Successfully. Welcome, "+ doctor.getUsername()+ " !");
            displayDashboard(doctor);
        }else{
            docview.displayMessage("Invalid Credentials");
            handleDoctorFlow();
        }
    }
    
    public void displayDashboard(Doctor doctor){
        while(true){
            try{
                int choice = docview.displayDashboard();
                switch(choice){
                     case 1:
                         docview.displayMessage("====== View Booking Appointment ====== ");
                         showBookingAppointment();
                         break;
                     case 2:
                         docview.displayMessage("====== Delete Appointment =======");
                         deleteAppointment();
                         break;
                     case 3:
                         docview.displayMessage("====== View Personal Details ======");
                         viewPersonalDetails(doctor);
                         break;
                     case 4:
                          docview.displayMessage("===== Update for Availability Schedule ======");
                          updatePersonalDetails();
                     case 5:
                          docview.displayMessage("===== Logging out ===== ");
                          System.exit(0);
                     default:
                          docview.displayMessage("Invalid option. Try Again");
                          displayDashboard(doctor);
         
                }
            
            }catch (Exception e){
                System.out.println("Invalid Input, Please input number only");
            }
            
        }
    }
    
    public void showBookingAppointment(){
        
    }
    
    public void deleteAppointment(){
        
    }
    public void viewPersonalDetails(Doctor doctor){
        docview.displayDoctorDetails(doctor);
    }
    public void updatePersonalDetails(){
        
    }
   
}
