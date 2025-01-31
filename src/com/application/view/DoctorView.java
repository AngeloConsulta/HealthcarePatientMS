/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.view;

import com.application.model.Doctor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class DoctorView {
    private Scanner sc = new Scanner(System.in);
    //This section is for Doctor's to access
    public Doctor displayLoginPrompt(){ //Di pa tapos Login the Doctor user
        Doctor doctor = new Doctor();
        System.out.println("\nLogin to Doctor Dashboard");
        System.out.print("Enter Username: ");
        doctor.setUsername(sc.nextLine());
        System.out.print("Enter Password: ");
        doctor.setPassword(sc.nextLine());
        
        return doctor;
    }
    
   
    public int handleLoginDoctor(){
        System.out.println("\nDoctors Dashboard System");
        System.out.println("[1]  Login");
        System.out.println("[2]  Back to Main Menu");
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    
    
    public int displayDashboard(){
        System.out.println("\nWelcome to the Doctors Dashboard");
        System.out.println("\n");
        System.out.println("[1] View Booking Appointment");
        System.out.println("[2] Delete Appointment ");
        System.out.println("[3] View Personal Details");
        System.out.println("[4] Update for Availability Schedule");
        System.out.println("[5] Logout");
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    
     public void displayDoctorDetails(Doctor doctor) {

        System.out.println("Username: " + doctor.getUsername());
        System.out.println("Full Name: " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Contact Number: " + doctor.getLicenseNumber());
        System.out.println("Contact Number: " + doctor.getContactNumber());
        System.out.println("Gender: " + doctor.getGender());
        System.out.println("Date of Birth: " + doctor.getDOB());
        System.out.println("Address: " + doctor.getAddress());
        System.out.println("Availability Status: " + doctor.getAvailabilityStatus());
        System.out.println("======================================");
    }
    
    
    //This Line section is for Admin to Access
    public void displayMessage(String message){
        System.out.println(message);
    }
    
    public String getInput(){
        return sc.nextLine();
    }
    
    public int getDoctorManagementChoice(){
        System.out.println("\nManage Doctor's Information:");
        System.out.println("[1] Add New Doctor");
        System.out.println("[2] View All Doctor");
        System.out.println("[3] Update Doctor Information");
        System.out.println("[4] Delete Doctor Record");
        System.out.println("[5] Back to Admin Dashboard");
        System.out.print("\nEnter your choice: ");
        return sc.nextInt();
        
    }
    //This is for Create API CRUD 
    public Doctor getDoctorDetails(){
        Doctor doctor = new Doctor();
        System.out.print("Enter Username: ");
        doctor.setUsername(sc.next());
        System.out.print("Enter Password: ");
        doctor.setPassword(sc.next());
        System.out.print("Enter Doctor's Name: ");
        doctor.setName(sc.next()+ sc.nextLine());
        System.out.print("Enter Specialization: ");
        doctor.setSpecialization(sc.next());
        System.out.print("Enter Licensed Number: ");
        doctor.setLicenseNumber(sc.next());
        System.out.print("Enter Contact Number: ");
        doctor.setContactNumber(sc.next());
        System.out.print("Gender: ");
        doctor.setGender(sc.next());
        LocalDate DOB = null;
        LocalDate dateOfBirth = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (dateOfBirth == null) {
            System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
            String input = sc.nextLine().trim();

            try {
                dateOfBirth = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            }
        }

        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.print("Enter Address: ");
        doctor.setAddress(sc.next()+ sc.nextLine());

        return doctor;
    }
    //This is a CRUD API for VIEW or READ which is di pa tapos
    public void displayDoctorTable(List<Doctor> doctors) {
    String format = "| %-5s | %-15s | %-20s | %-20s | %-20s | %-15s | %-10s | %-15s | %-25s | %-12s |\n";
    
    System.out.println("------------------------------------------------------------------------------------------------------------"
                       + "---------------------------------------------------------------------------------");
    System.out.printf(format, "ID", "Username", "Name", "Specialization", "License No.","Contact No." ,"Gender", "Date of Birth", "Address", "Availability");
    System.out.println("------------------------------------------------------------------------------------------------------------"
                       + "---------------------------------------------------------------------------------");
    
    for (Doctor doctor : doctors) {
        System.out.printf(format,
            doctor.getId(),
            doctor.getUsername(),
            doctor.getName(),
            doctor.getSpecialization(),
            doctor.getLicenseNumber(),
            doctor.getContactNumber(),
            doctor.getGender(),
            (doctor.getDOB() != null ? doctor.getDOB().toString() : "N/A"),
            doctor.getAddress(),
            (doctor.getAvailabilityStatus() != null ? doctor.getAvailabilityStatus().toString() : "N/A")
        );
    }
    System.out.println("------------------------------------------------------------------------------------------------------------"
                       + "---------------------------------------------------------------------------------");
    }
    public void viewAllDoctorForPatient(List<Doctor> doctors){
         String format = "| %-4s | %-20s | %-15s | %-20s | %-15s |\n";
        
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf(format, "ID", "Name", "Specialization", "Address", "Availability");
        System.out.println("------------------------------------------------------------------------------------------");

       // Loop through the doctors list and display only the required fields
        for (Doctor doctor : doctors) {
        System.out.printf(format, 
                      doctor.getId(), doctor.getName(), 
                      doctor.getSpecialization(), doctor.getAddress(), 
                      doctor.getAvailabilityStatus() != null ? doctor.getAvailabilityStatus().toString() : "N/A");
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }
    
    
    // This the UPDATE CRUD API
    // Display a prompt and get integer input for doctor ID
    public int getDoctorIdInput(String prompt) {
        System.out.print(prompt);
        return sc.nextInt();
    }

    // Get updated value or keep the current value if input is blank
    public String getInputOrDefault(String fieldName, String currentValue) {
        System.out.print(fieldName + " [" + currentValue + "]: ");
        String input = sc.nextLine();
       return input.isEmpty() ? currentValue : input;
    }

    // Get updated date or keep the current date if input is blank
    public LocalDate getDateInputOrDefault(String fieldName, LocalDate currentValue) {
        System.out.print(fieldName + " [" + (currentValue != null ? currentValue.toString() : "N/A") + "]: ");
        String input = sc.nextLine();
        return input.isEmpty() ? currentValue : LocalDate.parse(input);
    }


    public int displayDocDelChoice(){ //This is for Displaying Delete choice
        System.out.println("\nManage Doctor's Information:");
        System.out.println("[1] Archive: ");
        System.out.println("[2] Restore: ");
        System.out.println("[3] Permanently delete ");
        System.out.println("[4] Back ");
        System.out.print("\nEnter your choice: ");
        return sc.nextInt();
        
    } 
//    This is for DELETE CRUD Operation Soft Delete
    public int getDoctorIdInp(String message){
        System.out.print(message);
        return sc.nextInt();
    }

    public int getRestoreChoice() {
        System.out.println("\n[1] Restore a Doctor's record");
        System.out.println("[2] Back");
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }
    
}
