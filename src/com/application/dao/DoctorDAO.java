/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.dao;

import com.application.model.Doctor;
import com.application.model.Patient;
import com.application.util.DBConnection;
import com.application.util.QueryConstant;
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
public class DoctorDAO implements QueryConstant {

     //This method is only accessible in Doctor's: This is needed to M
     public Doctor getDoctorLogin(String username , String password) {
       
        Doctor doctor = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DOC_LOGIN)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor();
                    doctor.setId(rs.getInt("doc_id"));
                    doctor.setUsername(rs.getString("doc_username"));
                    doctor.setPassword(rs.getString("doc_password"));
                    doctor.setName(rs.getString("doc_fullname"));
                    doctor.setSpecialization(rs.getString("doc_specialization"));
                    doctor.setLicenseNumber(rs.getString("doc_license_number"));
                    doctor.setContactNumber(rs.getString("doc_contact_number"));
                    doctor.setGender(rs.getString("doc_gender"));
                    doctor.setDOB(rs.getDate("doc_dob").toLocalDate());
                    doctor.setAddress(rs.getString("doc_address"));
                    Date availabilityDate = rs.getDate("doc_availability_status");
            if (availabilityDate != null) {
                doctor.setAvailabilityStatus(availabilityDate.toLocalDate());
            }
                    
                
            }

        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return doctor;
    }   

     
     
    
    public boolean createAccount(Doctor doctor){ //This is for Creation of record by Admin
        try (Connection con = DBConnection.getConnection()){       
            PreparedStatement stmt = con.prepareStatement(DOC_CREATEACCOUNT);
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

     public boolean updateDoctor(Doctor doctor){
           try(Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(DOC_UPDATE)){
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
     //Soft Delete (Archive)
     public boolean softdDeletePatient(int id){
 
        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(DOC_ARCHIVE)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
            
    }
    //Restore the Archived Patient
    public boolean restorePatient(int id){
        
        try(Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(DOC_RESTORE)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    //Hard Delete or Permanently delete
    public boolean hardDeletePatient(int id){
   
        try(Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(DOC_PERMANENTDELETE)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    //This will serve as the VIEW CRUD API
    //Get All the Active Doctor (Exclude Archive / SoftDeleted)
    public List<Doctor> getAllActiveDoctor(){
        List<Doctor> doctors = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(DOC_VIEWACTIVEDOCTOR );
                ResultSet rs = stmt.executeQuery() ){
            while(rs.next()){
                Doctor doctor = mapResultSetToDoctor(rs);
                doctors.add(doctor);
            
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctors;
    }
    // Get All Archived Doctor
    public List<Doctor> getArchivedDoctor() {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DOC_VIEWARCHIVE);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Doctor doctor = mapResultSetToDoctor(rs);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
    //This will serve as Implementation of View API
    // Helper method to map ResultSet to Doctor object
    private Doctor mapResultSetToDoctor(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("doc_id"));
        doctor.setUsername(rs.getString("doc_username"));
        doctor.setName(rs.getString("doc_fullname"));
        doctor.setSpecialization(rs.getString("doc_specialization"));
        doctor.setLicenseNumber(rs.getString("doc_license_number"));
        doctor.setContactNumber(rs.getString("doc_contact_number"));
        doctor.setGender(rs.getString("doc_gender"));
        Date dobsql = rs.getDate("doc_dob");
        doctor.setDOB(dobsql !=null ? dobsql.toLocalDate(): null); // Convert java.sql.Date to LocalDate
        doctor.setAddress(rs.getString("doc_address"));
        Date availsql = rs.getDate("doc_availability_status");
        doctor.setAvailabilityStatus(availsql !=null?availsql.toLocalDate(): null);
        return doctor;
    }

     
}
