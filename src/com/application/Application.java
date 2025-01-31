/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application;

import com.application.controller.AdminController;
import com.application.controller.DoctorController;
import com.application.controller.PatientController;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Application {
    
    public void mainMenu() {
        AdminController adminController = new AdminController();
        PatientController ptController = new PatientController();
        DoctorController docController = new DoctorController();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Healthcare Patient Management System");
        System.out.println("Please select your role:");
        System.out.println("[1] Admin");
        System.out.println("[2] Doctor");
        System.out.println("[3] Patient");

        System.out.print("\nEnter your choice: ");
        
        try{
           int choice = sc.nextInt();
           switch (choice) {
            case 1 :
                adminController.handleAdminFlow();
                break;
            case 2 :
                docController.handleDoctorFlow();
                break;
            case 3 :
                ptController.handlePatientFlow();
                break;
            case 4 :
                System.out.println("Exiting...");
                System.exit(0);
            
            default : 
                System.out.println("Invalid choice. Please try again.");
                mainMenu();
            }
        }catch(Exception e){
             System.out.println("Invalid Input, Please input number only");
             mainMenu();
        }
      

        
    }

}
