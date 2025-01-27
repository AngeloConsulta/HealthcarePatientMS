/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.view;

import com.application.model.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class PatientView {
    private Scanner sc = new Scanner(System.in);
    
    public Patient displayLoginPrompt(){
        System.out.println("\nLogin to Patient Dashboard ");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        return new Patient (0,username, password, null,null,null, null, null,null,null);
    }
    
    public void displayPasswordPrompt(){
        System.out.print("Enter password: ");
    }
    public int handleLoginPatient(){
        System.out.println("\n Patient Dashboard System");
        System.out.println("[1]  Login");
        System.out.println("[2]  Back to Main Menu");
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    
    public int displayDashboard(){
        System.out.println("\nWelcome to the Patient Dashboard");
        System.out.println("1. Book an Appointment");
        System.out.println("2. View Upcoming Appointments");
        System.out.println("3, Cancel an Appointment");
        System.out.println("4. Logout");
        
        System.out.print("\nEnter your choice: ");
        int choice=sc.nextInt();
        sc.nextLine();
        return choice;
    }
    public void displayMessage(String message){
        System.out.println(message);
    }
    
    public String getInput(){
        return sc.nextLine();
    }
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
        sc.nextLine();
        System.out.print("\nCreate and Add Patient details");
        System.out.print("\nUsername: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Firstname: ");
        String first_name = sc.nextLine();
        System.out.print("Middlename: ");
        String middle_name= sc.nextLine();
        System.out.print("Lastname: ");
        String last_name = sc.nextLine();
        System.out.print("Date of Birth (yyyy-mm-dd): ");
        String dobInput=sc.nextLine();
        LocalDate dob = LocalDate.parse(dobInput, DateTimeFormatter.ISO_DATE);
        System.out.print("Gender: ");
        String  gender = sc.nextLine();
        System.out.print("Contact Number: ");
        String contact_number = sc.nextLine();
       
        System.out.print("Address: ");
        String address=sc.nextLine();
        
        return new Patient(username, password,first_name,last_name,middle_name, dob, gender, contact_number,address);
    }
    public void displayPatients(List<Patient> patients){
        System.out.println("\nList of Patients: ");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-15s %-10s %-10s %-15s %-30s\n",
        "ID", "Username", "First Name", "Last Name", "Gender", "DOB", "Contact", "Address");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        
        for (Patient patient : patients){
            System.out.printf("%-5d %-15s %-15s %-15s %-10s %-10s %-15s %-30s\n",
                    patient.getId(),
                    patient.getUsername(),
                    patient.getFirst_name(),
                    patient.getLast_name(),
                    patient.getGender(),
                    patient.getDob(),
                    patient.getContact_number(),
                    patient.getAddress());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        
        
    
    }
    public int displayBackOption() {
    System.out.println("\n[1] Back to Manage Patient Records");
    System.out.print("\nEnter your choice: ");
    return sc.nextInt();
}

    
        
}
