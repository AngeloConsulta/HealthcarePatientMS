/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.view;

import com.application.model.Doctor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class DoctorView {
    private Scanner sc = new Scanner(System.in);
    
    public void displayLoginPrompt(){
        System.out.print("Enter username: ");
    }
    
    public void displayPasswordPrompt(){
        System.out.print("Enter password: ");
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
    
    
    public void displayDashboard(){
        System.out.println("\nWelcome to the Doctors Dashboard");
        System.out.println("1. View Appointment");
        System.out.println("2. Approve/Reject Appointment");
        System.out.println("3. View Patients Report/Record");
        System.out.println("4. Logout");
        
        System.out.print("\nEnter your choice");
        int doctorchoice=sc.nextInt();
        sc.nextLine();
    }
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
    public Doctor getDoctorDetails(){
        System.out.print("Enter Username: ");
        String username =sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();
        sc.nextLine();
        System.out.print("Enter Doctor's Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();
        System.out.print("Enter Licensed Number: ");
        String licenseNumber = sc.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        LocalDate DOB = null;
        while (DOB == null) {
            try {
               System.out.print("Date of Birth (yyyy-mm-dd): ");
               String dobInput = sc.nextLine().trim();
               DOB = LocalDate.parse(dobInput, DateTimeFormatter.ISO_DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
        }
        }
        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        return new Doctor(username, password, name, specialization, contactNumber, address,licenseNumber,gender,DOB );
    }
    public void displayDoctorTable(List<Doctor> doctors){
        System.out.printf("%-15s %-15s %-20s %-20s %-20s %-15s %-10s %-15s %-30s\n", 
        "Username", "Name", "Specialization", "License No.", "Contact No.", "Gender", "DOB", "Address");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        for (Doctor doctor : doctors) {
        System.out.println(doctor.toString());
        }
    }
    
}
