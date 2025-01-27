/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.dao;

import com.application.model.Doctor;
import com.application.model.Patient;
import com.application.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DoctorDAO{

  
     public boolean verifyCredentials(String username, String password){
        try (Connection con = DBConnection.getConnection()){
            String query = "SELECT * FROM doctor WHERE username =? AND password =? ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString( 1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
     }
     public boolean createAccount(Doctor doctor){
        try (Connection con = DBConnection.getConnection()){
            String query = "INSERT INTO doctor (username, password, name, specialization, licenseNumber, contactNumber, gender, DOB, address )VALUES(?,?,?,?,?,?,?,?,?) ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, doctor.getUsername());
            stmt.setString(2, doctor.getPassword());
            stmt.setString(3, doctor.getName());
            stmt.setString(4, doctor.getSpecialization());
            stmt.setString(5, doctor.getLicenseNumber());
            stmt.setString(6, doctor.getContactNumber());
            stmt.setString(7, doctor.getGender());
            LocalDate DOB = doctor.getDOB();
            stmt.setDate(8, Date.valueOf(DOB));
            stmt.setString(9, doctor.getAddress());
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
     }
     public List<Doctor> getAllDoctors(){
         List<Doctor> doctorList = new ArrayList<>();
         String query = "SELECT *FROM doctor";
         try(Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
             while(rs.next()){
                 int id = rs.getInt("id");
                 String username = rs.getString("username");
                 String fullName = rs.getString("fullName");
                 String contactNumber = rs.getString("contactNumber");
                 String gender = rs.getString("gender");
                 LocalDate dob = rs.getDate("DOB").toLocalDate(); // Convert java.sql.Date to LocalDate
                 String address = rs.getString("address");

             }
         }   catch (SQLException ex) {
             Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return doctorList;
     }
}
