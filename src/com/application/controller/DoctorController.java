/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controller;

import com.application.Application;

import com.application.model.Doctor;
import com.application.view.DoctorView;
import com.application.controller.AdminController;
import com.application.daoimpl.DoctorDAOIMPL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Administrator
 */
public class DoctorController {
    private final DoctorView docview = new DoctorView();
    private final DoctorDAOIMPL docDAO = new DoctorDAOIMPL();
    private final Application app = new Application();
    private final AdminController admControl = new AdminController();
  
    
    public void manageDoctors(){
         while(true){
            try{
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
                    docview.displayMessage("Invalid Choice, Please try again");
                    admControl.handleDashboard();
            }
            }catch(Exception e){
                docview.displayMessage("Invalid Input, Please input number only");
                admControl.handleDashboard();
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
//    public void viewAllDoctorByPatient(){
//        List<Doctor> doc = docDAO.getAllActiveDoctor();
//        docview.viewAllDoctorForPatient(doc);
//    }
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
   
    
    //Set the Updated Values
     doctor.setUsername( newUsername);
     doctor.setName(newName);
     doctor.setSpecialization(newSpecialization);
     doctor.setLicenseNumber(newLicenseNumber);
     doctor.setContactNumber( newContactNumber);
     doctor.setGender(newGender);
     doctor.setDOB(newDOB );
     doctor.setAddress(newAddress);
   
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
            try{
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
            }catch(Exception e){
                docview.displayMessage("Invalid Input, Please input number only");
                manageDoctors();
            }
        }
    }
     public void archiveDeleteDoctor(){
        viewAllDoctor();
        int id = docview.getDoctorIdInp("Enter the ID of the doctor to archive: ");
        boolean success =  docDAO.softdDeleteDoctor(id);
        if (success){
            docview.displayMessage( "Doctor archived successfully! ");
            viewAllDoctor();
        }else{
            docview.displayMessage("Failed to archived patient.");
        }
    }
    public void restoreArchiveDoctor(){
       List<Doctor> archiveDoctor = docDAO.getArchivedDoctor();
       docview.displayDoctorTable(archiveDoctor);
       
       int choice = docview.getRestoreChoice();
       if(choice ==1){
           int patient_id = docview.getDoctorIdInp("Enter the ID of patient to restore: ");
           boolean success = docDAO.restoreDoctor(choice);
           if (success){
               docview.displayMessage( "Doctor's Record restored successfully! ");
               viewAllDoctor();
           }else{
               docview.displayMessage("Failed to restore Doctor's record");
           }
       }
    }
    public void permanentlyDelete(){
        viewAllDoctor();
        int doc_id = docview.getDoctorIdInp("Enter the ID of the patient to permanently delete: ");
        boolean success = docDAO.hardDeleteDoctor(doc_id);
        if (success){
            docview.displayMessage("Doctor record permanently deleted");
            viewAllDoctor();
        }else{
            docview.displayMessage( "Failed to permanently delete");
        }
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
                    app.mainMenu();
               }
            }catch(Exception e){
                System.out.println("Invalid Input, Please input number only");
                app.mainMenu();
            }
            
        }
    }
     
    public void loginDoctor(){
        Doctor doc =docview.displayLoginPrompt();
        Doctor doctor =docDAO.getDoctorLogin(doc.getUsername(), doc.getPassword());
        if (doctor != null){
            docview.displayMessage("\nLogin Successfully. Welcome, Dr. "+ doctor.getUsername()+ " !");
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
                         docview.displayMessage("=========== View Booking Appointment =========== ");
                         showBookingAppointment();
                         break;
                     case 2:
                         docview.displayMessage("============ Delete Appointment =============");
                         deleteAppointment();
                         break;
                     case 3:
                         docview.displayMessage("======= View Personal Details ========");
                         viewPersonalDetails(doctor);
                         break;
                     case 4:
                          docview.displayMessage("========= Add Schedule ========");
                          addSchedule(doctor);
                          break;
                     case 5:
                          docview.displayMessage("===== Logging out ===== ");
                          System.exit(0);
                          break;
                     default:
                          docview.displayMessage("Invalid option. Try Again");
                          displayDashboard(doctor);
         
                }
            
            }catch (Exception e){
                System.out.println("Invalid Input, Please input number only");
                displayDashboard(doctor);
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
    public void addSchedule(Doctor doctor ) {
        Doctor doctorWithSched = docview.addSchedule(doctor);
        
//        Doctor loggedInDoctor = docview.addSchedule();
        boolean result = docDAO.addSchedule(doctorWithSched);
        if (result) {
            docview.showSuccessMessage("Schedule successfully added for Dr. " + doctor.getName());
        } else {
            docview.showErrorMessage("Failed to add schedule.");
        }

    }
   
}
