/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.view;

import java.util.Scanner;
import com.application.model.Admin;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class AdminView {
    private Scanner sc = new Scanner(System.in);
    
    
    public int handleAdminLogin(){
        System.out.println("\nAdmin Dashboard System");
        System.out.println("\n[1]  Login");
        System.out.println("[2]  Back to Main Menu");
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
    
    
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
//    public Admin getAdminDetails(){
//        System.out.println("\nRegister Admin:");
//        System.out.print("Enter Username: ");
//        String username = sc.nextLine();
//        System.out.print("Enter Password: ");
//        String password = sc.nextLine();
//        System.out.print("Enter Name: ");
//        String name = sc.nextLine();
//        System.out.print("Enter Age: ");
//        int age = sc.nextInt();
//        sc.nextLine(); // Consume the newline
//        System.out.print("Enter Gender: ");
//        String gender = sc.nextLine();
//
//        return new Admin(username, password, name, age, gender);
//      
//        
//    } 
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
        System.out.println("[3] Search Patient or Doctor");
        System.out.println("[4] Logout");
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
       
    }
    public void displaySearchResults(List<Object[]> results){
         if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("\nSearch Results:");
            System.out.printf("%-10s | %-5s | %-15s | %-25s | %-20s\n",
                    "Type", "ID", "Username", "Name", "Extra Info");
            System.out.println("-------------------------------------------------------------------------------");

            for (Object[] data : results) {
                System.out.printf("%-10s | %-5d | %-15s | %-25s | %-20s\n",
                        data[0], data[1], data[2], data[3], data[4]);
            }
        }
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