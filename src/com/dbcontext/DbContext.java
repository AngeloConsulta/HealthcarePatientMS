/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbcontext;

/**
 *
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbContext {
   

    private static final String DB_URL = "jdbc:mysql://localhost:3306/patients";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        displayPatients();
    }

    public static void displayPatients() {
        String query = "SELECT * FROM patients";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.printf("%-10s %-20s %-5s %-10s %-30s %-15s%n", 
                              "PatientID", "Name", "Age", "Gender", "Address", "Phone Number");
            System.out.println("------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int patientId = rs.getInt("PatientID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");

                System.out.printf("%-10d %-20s %-5d %-10s %-30s %-15s%n", 
                                  patientId, name, age, gender, address, phoneNumber);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


