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
            String query = "INSERT INTO patient (username, password, full_name, dob ,gender,contact_number,address )VALUES(?,?,?,?,?,?,?,?,?) ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, patient.getUsername());
            stmt.setString(2, patient.getPassword());
            stmt.setString(3, patient.getFull_name());
            //Convert patientt.getDob() to java.sql.Date
            LocalDate dob = patient.getDob();
            stmt.setDate(4, Date.valueOf(dob));
            stmt.setString(5, patient.getGender());
            stmt.setString(6, patient.getContact_number());
            stmt.setString(7, patient.getAddress());
           
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    
           
    }
    //VIEW CRUD API
    public List<Patient> getAllPatient(){
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";
        
          try (Connection con = DBConnection.getConnection(); // Establish connection
             PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                Patient patient = new Patient();
                    patient.setId(rs.getInt("patient_id"));
                    patient.setUsername(rs.getString("username"));
                    patient.setPassword(rs.getString("password"));
                    patient.setFull_name(rs.getString("full_name"));
                    Date dob = rs.getDate("dob");
                    patient.setDob(dob !=null ? dob.toLocalDate(): null);
                    patient.setGender(rs.getString("gender"));
                    patient.setContact_number(rs.getString("contact_number"));
                    patient.setAddress(rs.getString("address"));
                    patient.setEmergency_contact_name(rs.getString("emergency_contact_name"));
                    patient.setEmergency_contact_number(rs.getString("emergency_contact_number"));
                    patient.setBlood_type(rs.getString("blood_type"));
                    patient.setMedical_conditions(rs.getString("medical_conditions"));
                    patient.setMedications(rs.getString("medications"));
                    patient.setAllergies(rs.getString("allergies"));
                    
                    patients.add(patient);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;

    }
    //UPDATE CRUD API
    public boolean updatePatient(Patient patient){
         String query = "UPDATE patient SET username = ?, password = ?, full_name = ?, dob = ?, gender = ?, contact_number = ?, address = ? WHERE patient_id = ?";
         try (Connection con = DBConnection.getConnection();
              PreparedStatement stmt = con.prepareStatement(query)) {
             
             stmt.setString(1, patient.getUsername());
             stmt.setString(2, patient.getPassword());
             stmt.setString(3, patient.getFull_name());
             stmt.setDate(4, patient.getDob()!=null? java.sql.Date.valueOf(patient.getDob()): null);
             stmt.setString(5, patient.getGender());
             stmt.setString(6, patient.getContact_number());
             stmt.setString(7, patient.getAddress());
             stmt.setInt(8, patient.getId());
             
             int rowsAffected = stmt.executeUpdate();
             return rowsAffected > 0;
         }catch(SQLException e){
              Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
          return false;
         }
    }
        
    
    
    
}
