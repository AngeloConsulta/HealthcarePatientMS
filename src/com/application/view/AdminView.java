/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.view;

import java.util.Scanner;
import com.application.model.Admin;

/**
 *
 * @author Administrator
 */
public class AdminView {
    private Scanner sc = new Scanner(System.in);
    
    
    
    public void showWelcomeMessage(){
        System.out.println("Welcome to the Healthcare Patient Management System");
    }
    public int getRoleSelection() {
        System.out.println("[1] Admin");
        System.out.println("[2] Doctor");
        System.out.println("[3] Patient");
        System.out.println("[4] Exit");
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }
    public Admin getAdminDetails(){
        System.out.println("\nRegister Admin:");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // Consume the newline
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();

        return new Admin(username, password, name, age, gender);
      
        
    } 
    public Admin getLoginDetails() {
        System.out.println("\nAdmin Login:");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        return new Admin(username, password, null, 0, null); // Only username and password are needed for login
    }
    
    public int getAdminDashboardChoice(){
        System.out.println("\nWelcome to the Admin Dashboard");
        System.out.println("[1] Manage Patients Record Information");
        System.out.println("[2] Manage Doctors Information");
        System.out.println("[3] Logout");
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
       
    }
    public int getchoice(){
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    public void displayMessage(String message){
        System.out.println(message);
    }
    
    public String getInput(){
        return sc.nextLine();
    }

  
}