/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.view;

import com.application.model.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class PatientView {
    private Scanner sc = new Scanner(System.in);
    //This is for patient side only
    public Patient displayLoginPrompt(){
        Patient patient = new Patient();
        System.out.println("\nLogin to Patient Dashboard ");
        System.out.print("Enter Username: ");
        patient.setUsername(sc.nextLine());
        System.out.print("Enter password: ");
        patient.setPassword(sc.nextLine()); 
        
        return patient;
    }
    //This is for patient side only
    public int handleLoginPatient(){
        System.out.println("\n Patient Dashboard System");
        System.out.println("[1]  Login");
        System.out.println("[2]  Back to Main Menu");
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    //This is for the Patient Side only
    public int displayDashboard(){
        System.out.println("\nWelcome to the Patient Dashboard");
        System.out.println("[1] View Personal Details");
        System.out.println("[2] Update Personal Info");
        System.out.println("[3] View Available Doctors");
        System.out.println("[4] Book an Appointment");
        System.out.println("[5] Cancel an Appointment");
        System.out.println("[6] Logout");
        
        System.out.print("\nEnter your choice: ");
        int choice=sc.nextInt();
        sc.nextLine();
        return choice;
    }
    //This is View API Access by Patient User
    public void displayPatientDetails(Patient patient) {
       
        System.out.println("Username: " + patient.getUsername());
        System.out.println("Full Name: " + patient.getFull_name());
        System.out.println("Date of Birth: " + patient.getDob());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Contact Number: " + patient.getContact_number());
        System.out.println("Address: " + patient.getAddress());
        System.out.println("Emergency Contact: " + patient.getEmergency_contact_name() + " (" + patient.getEmergency_contact_number() + ")");
        System.out.println("Blood Type: " + patient.getBlood_type());
        System.out.println("Medical Conditions: " + patient.getMedical_conditions());
        System.out.println("Medications: " + patient.getMedications());
        System.out.println("Allergies: " + patient.getAllergies());
        System.out.println("==================================================");
        
    }
   
    public void displayMessage(String message){
        System.out.println(message);
    }
    
    public String getInput(){
        return sc.nextLine();
    }
    
    //This is for the Admin Access
    public int getPatientManagementChoice(){
        System.out.println("\nManage Patient Records:");
        System.out.println("[1] Add New Patient");
        System.out.println("[2] View All Patients");
        System.out.println("[3] Update Patient Information");
        System.out.println("[4] Delete Patient Record");
        System.out.println("[5] Back to Admin Dashboard");
        System.out.print("\nEnter your choice: ");
        return sc.nextInt();
    }
    
    public Patient getPatientDetails(){
        Patient patient = new Patient();
        System.out.print("\nCreate and Add Patient details");
        System.out.print("\nUsername: ");
        patient.setUsername(sc.next());
        System.out.print("Password: ");
        patient.setPassword(sc.next());
        System.out.print("Fullname: ");
        patient.setFull_name(sc.next()+  sc.nextLine());
        LocalDate dob = null;
        while (dob == null) {
            try {
               System.out.print("Date of Birth (yyyy-mm-dd): ");
               String dobInput = sc.nextLine().trim();
               dob = LocalDate.parse(dobInput, DateTimeFormatter.ISO_DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter in yyyy-mm-dd format."+ e);
        }
        }
        patient.setDob(dob);
        System.out.print("Gender: ");
        patient.setGender(sc.next());
        System.out.print("Contact Number: ");
        patient.setContact_number(sc.next());
        System.out.print("Address: ");
        patient.setAddress(sc.next()+sc.nextLine());
        
        return patient;
    }
    
    //This is for Update CRUD API
    public int getPatientIdInput(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next(); // Clear invalid input
            System.out.print(prompt);
        }
        return sc.nextInt();
    }

    // Get string input with a default value
    public String getInputOrDefault(String fieldName, String currentValue) {
        System.out.print(fieldName + " [" + currentValue + "]: ");
        String input = sc.nextLine().trim();
        return input.isEmpty() ? currentValue : input;
    }

    // Get LocalDate input with a default value
    public LocalDate getDateInputOrDefault(String fieldName, LocalDate currentValue) {
        System.out.print(fieldName + " [" + (currentValue != null ? currentValue.toString() : "N/A") + "]: ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return currentValue;
        }
        try {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
            return getDateInputOrDefault(fieldName, currentValue);
        }
    }

    
    public void displayPatients(List<Patient> patients){
        String format = "| %-5s | %-10s | %-10s  | %-30s | %-15s | %-10s | %-20s | %-30s | %-30s | %-30s | %-10s |  %-30s |  %-30s |  %-20s |\n";
        System.out.println("\nList of Patients: ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf( format, "ID", "Username","Password", "Full Name", "Date of Birth", "Gender", "Contact No.", "Address", "Emergency Contact Name", "Emergency Contact No.", "Blood Type","Medical Condition","Medication","Allergies");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        for (Patient patient : patients){
            System.out.printf(format,
                    patient.getId(),
                    patient.getUsername(),
                    patient.getPassword(),
                    patient.getFull_name(),
                    (patient.getDob() != null ? patient.getDob().toString() : "N/A"),
                    patient.getGender(),
                    patient.getContact_number(),
                    patient.getAddress(),
                    patient.getEmergency_contact_name(),
                    patient.getEmergency_contact_number(),
                    patient.getBlood_type(),
                    patient.getMedical_conditions(),
                    patient.getMedications(),
                    patient.getAllergies());
            
            
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        
    
    }
    
    public int displayDelChoice(){ //This is for Displaying Delete choice
        System.out.println("\nManage Patient's Information:");
        System.out.println("[1] Archive: ");
        System.out.println("[2] Restore: ");
        System.out.println("[3] Permanently delete ");
        System.out.println("[4] Back ");
        System.out.print("\nEnter your choice: ");
        return sc.nextInt();
        
    }    
    public int getPatientIdInp(String message){
        System.out.print(message);
        return sc.nextInt();
    }

    public int getArchiveRestoreChoice() {
        System.out.println("\n[1] Restore a Patient record");
        System.out.println("[2] Back");
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }
    
        
}
