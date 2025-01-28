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
                    deleteDoctor();
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
        List<Doctor> doc = docDAO.getAllDoctors();
        docview.displayDoctorTable(doc);
    }
    //Update CRUD API
    private void updateDoctor(){
        // Display existing doctors
    List<Doctor> doctors = docDAO.getAllDoctors();
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
    doctor.setUsername(docview.getInputOrDefault("Username", doctor.getUsername()));
    doctor.setName(docview.getInputOrDefault("Name", doctor.getName()));
    doctor.setSpecialization(docview.getInputOrDefault("Specialization", doctor.getSpecialization()));
    doctor.setLicenseNumber(docview.getInputOrDefault("License Number", doctor.getLicenseNumber()));
    doctor.setContactNumber(docview.getInputOrDefault("Contact Number", doctor.getContactNumber()));
    doctor.setGender(docview.getInputOrDefault("Gender", doctor.getGender()));
    doctor.setDOB(docview.getDateInputOrDefault("DOB (YYYY-MM-DD)", doctor.getDOB()));
    doctor.setAddress(docview.getInputOrDefault("Address", doctor.getAddress()));
    doctor.setAvailabilityStatus(docview.getDateInputOrDefault("Availability Status (YYYY-MM-DD)", doctor.getAvailabilityStatus()));
    
    // Update the doctor in the database
    boolean success = docDAO.updateDoctor(doctor);
    if (success) {
        docview.displayMessage("Doctor updated successfully!");
    } else {
        docview.displayMessage("Failed to update the doctor. Please try again.");
    }
    }
    //Choice for Soft delete and hard delete
    private void deleteDoctor(){
        while (true){
            int choice = docview.displayDeleteChoice();
            switch(choice){
                case 1:
                    handleArchiveOrRestore();
                    break;
                case 2:
                    getHardDelete();
                    break;
                case 3:
                    manageDoctors();
                    break;
                default:
                    docview.displayMessage("Invalid Choice. Please Try again");
                    
                    
            }
        }
    }
    public void handleArchiveOrRestore(){
        List<Doctor> archivedDoctors = docDAO.getArchivedDoctors();
        docview.displayArchiveDoctors(archivedDoctors);
        
        int[] userChoice = docview.getArchiveChoice();//get user input from the view
        int doctorId = userChoice[0];
        int archiveChoice = userChoice[1];
        //perform archive/restore operation
        boolean success = docDAO.archiveOrRestoreDoctor(doctorId, archiveChoice);
        if (success){
            docview.displayMessage("Operation Successfully ");
        }else{
            docview.displayMessage("Operation Failed, Please Try Again");
        }
    }
    public void getHardDelete(){
        
    }
    //This Line of Code is Only for Access of Doctors
     public void handleDoctorFlow(){
        while(true){
            try{
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
                    handleDoctorFlow();
            }
            }catch(Exception e){
                System.out.println("Invalid Input, Please input number only");
                app.mainMenu();
            }
            
        }
    }
     
    public void login(){
        Doctor doctor =docview.displayLoginPrompt();
        boolean isValid =docDAO.verifyCredentials(doctor.getUsername(), doctor.getPassword());
        if (isValid){
            docview.displayMessage("\nLogin Successfully. Welcome, "+ doctor.getUsername()+ " !");
            displayDashboard();
        }else{
            docview.displayMessage("Invalid Credentials");
            handleDoctorFlow();
        }
    }
    
    public void displayDashboard(){
        while(true){
            try{
                int choice = docview.displayDashboard();
                switch(choice){
                     case 1:
                         docview.displayMessage("Booking an appointment..");
//                       bookAppointment();
                         break;
                     case 2:
                         docview.displayMessage("Viewing upcoming appointments..");
//                       viewAppointment();
                         break;
                     case 3:
                         docview.displayMessage("Canceling an appoitnment..");
//                       cancelAppointment();
                         break;
                     case 4:
                          docview.displayMessage("Logging out..");
                     default:
                          docview.displayMessage("Invalid option. Try Again");
                          displayDashboard();
         
                }
            
            }catch (Exception e){
                System.out.println("Invalid Input, Please input number only");
            }
            
        }
    }
   
}
