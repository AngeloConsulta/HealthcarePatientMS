/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.dao;

/**
 *
 * @author Administrator
 */

import com.application.model.Patient;
import com.application.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PatientDAO extends DBConnection{
   

    public boolean verifyCredentials(Patient patient){
        try (Connection con = DBConnection.getConnection()){
            String query = "SELECT * FROM patient WHERE username =? AND password =? ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString( 1, patient.getUsername());
            stmt.setString(2, patient.getPassword());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
       
    }
    public boolean createAccount(Patient patient){
        try (Connection con = DBConnection.getConnection()){
            String query = "INSERT INTO patient (username, password, first_name, last_name, middle_name, dob ,gender,contact_number,address )VALUES(?,?,?,?,?,?,?,?,?) ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, patient.getUsername());
            stmt.setString(2, patient.getPassword());
            stmt.setString(3, patient.getFirst_name());
            stmt.setString(4, patient.getLast_name());
            stmt.setString(5, patient.getMiddle_name());
            //Convert patientt.getDob() to java.sql.Date
            LocalDate dob = patient.getDob();
            stmt.setDate(6, Date.valueOf(dob));
            stmt.setString(7, patient.getGender());
            stmt.setString(8, patient.getContact_number());
            stmt.setString(9, patient.getAddress());
           
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    
           
    }
    public List<Patient> getAllPatient(){
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";
        
          try (Connection con = DBConnection.getConnection(); // Establish connection
             PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient(
                    rs.getInt("patient_id"),
                    rs.getString("username"),
                    null, // Password is excluded for security
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    null, // Middle name is excluded here
                    rs.getDate("dob").toLocalDate(),
                    rs.getString("gender"),
                    rs.getString("contact_number"),
                    rs.getString("address")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
        
    
    
    
}
