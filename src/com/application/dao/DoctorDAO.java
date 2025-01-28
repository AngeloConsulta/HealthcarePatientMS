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

     //This method is only accessible in Doctor's
     public boolean verifyCredentials(String username, String password){ //This is for Login of Doctors
        try (Connection con = DBConnection.getConnection()){
            String query = "SELECT * FROM doctor WHERE username =? AND password =? ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString( 1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
       
        }
         return false;
     }
     public boolean createAccount(Doctor doctor){ //This is for Creation of record by Admin
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
     public List<Doctor> getAllDoctors(){ //This is for View Crud in the Admin Side , di pa tapos
         List<Doctor> doctorList = new ArrayList<>();
         String query = "SELECT *FROM doctor";
         try(Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
             while(rs.next()){
                 Doctor doctor = new Doctor();
                 doctor.setId(rs.getInt("id"));
                 doctor.setUsername(rs.getString("username"));
                 doctor.setName(rs.getString("name"));
                 doctor.setSpecialization(rs.getString("specialization"));
                 doctor.setLicenseNumber(rs.getString("licenseNumber"));
                 doctor.setContactNumber(rs.getString("contactNumber"));
                 doctor.setGender(rs.getString("gender"));
                 Date dobsql = rs.getDate("DOB");
                 doctor.setDOB(dobsql !=null ? dobsql.toLocalDate(): null); // Convert java.sql.Date to LocalDate
                 doctor.setAddress(rs.getString("address"));
                 Date availsql = rs.getDate("availabilityStatus");
                 doctor.setAvailabilityStatus(availsql !=null?availsql.toLocalDate(): null);
                 doctorList.add(doctor);

             }
         }   catch (SQLException ex) {
             Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return doctorList;
     }
     public boolean updateDoctor(Doctor doctor){
          String query = "UPDATE doctor SET username = ?, name = ?, specialization = ?, licenseNumber = ?, "
                 + "contactNumber = ?, gender = ?, DOB = ?, address = ?, availabilityStatus = ? WHERE id = ?";
           try(Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(query)){
               stmt.setString(1, doctor.getUsername());
               stmt.setString(2, doctor.getName());
               stmt.setString(3, doctor.getSpecialization());
               stmt.setString(4, doctor.getLicenseNumber());
               stmt.setString(5, doctor.getContactNumber());
               stmt.setString(6, doctor.getGender());
               stmt.setDate(7, doctor.getDOB()!=null? java.sql.Date.valueOf(doctor.getDOB()): null);
               stmt.setString(8, doctor.getAddress());
               stmt.setDate(9, doctor.getAvailabilityStatus()!=null? java.sql.Date.valueOf(doctor.getAvailabilityStatus()): null);
               stmt.setInt(10, doctor.getId());
               int rowsAffected = stmt.executeUpdate();
               return rowsAffected > 0;
           }catch(SQLException e){
               e.printStackTrace();
           }
         return false;
     }
     
     
     
     
     
     
     
     public List<Doctor> getArchivedDoctors(){
         String query = "SELECT id, username,password, name, specialization, licenseNumber, contactNumber, gender, DOB, address FROM doctor WHERE archived = 1";
         List<Doctor> doctors = new ArrayList<>();
         try (Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()){
             while(rs.next()){
                 Doctor doctor = new Doctor();
                 doctor.setId(rs.getInt("id"));
                 doctor.setUsername(rs.getString("username"));
                 doctor.setPassword(rs.getString("password"));
                 doctor.setName(rs.getString("name"));
                 doctor.setSpecialization(rs.getString("specialization"));
                 doctor.setLicenseNumber(rs.getString("licenseNumber"));
                 doctor.setContactNumber(rs.getString("contactNumber"));
                 doctor.setGender(rs.getString("gender"));
                 doctor.setDOB(rs.getDate("DOB").toLocalDate());
                 doctor.setAddress(rs.getString("address"));
                 doctors.add(doctor);
             }
         }catch(SQLException e){
             System.out.println(e);
         }
         return doctors;
            
     }
     public boolean archiveOrRestoreDoctor(int doctorId, int archiveChoice){
         String query = "UPDATE doctor SET archived = ? WHERE id = ?";
         try (Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(query)){
             stmt.setInt(1, archiveChoice);
             stmt.setInt(2, doctorId);
             int rowsUpdated = stmt.executeUpdate();
             return rowsUpdated > 0;
         } catch(SQLException e){
             System.out.println(e);
         }
//         finally{
//             con.close(); // this is need to implement
          return false; 
     }
     
}
