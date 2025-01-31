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
import java.sql.Statement;


public class PatientDAO extends DBConnection{
   

   
    
    public Patient getPatientLogin(String username , String password) {
        String query = "SELECT * FROM tblpatientinfo WHERE pat_username = ? AND pat_password = ?";
        Patient patient = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient();
                    patient.setId(rs.getInt("pat_id"));
                    patient.setUsername(rs.getString("pat_username"));
                    patient.setPassword(rs.getString("pat_password"));
                    patient.setFull_name(rs.getString("pat_fullname"));
                    
                    patient.setDob(rs.getDate("pat_dob").toLocalDate());
                    patient.setGender(rs.getString("pat_gender"));
                    patient.setContact_number(rs.getString("pat_contactnumber"));
                    patient.setAddress(rs.getString("pat_address"));
                    patient.setEmergency_contact_name(rs.getString("pat_emergency_contactname"));
                    patient.setEmergency_contact_number(rs.getString("pat_emergency_contactno"));
                    patient.setBlood_type(rs.getString("pat_bloodtype"));
                    patient.setMedical_conditions(rs.getString("pat_medicalconditions"));
                    patient.setMedications(rs.getString("pat_medications"));
                    patient.setAllergies(rs.getString("pat_allergies"));
                    
                
            }

        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return patient;
    }

    
    public boolean updatePatientPersonalDetails(Patient patient) {
    String query = "UPDATE tblpatientinfo SET  pat_contactnumber = ?, pat_address = ?, pat_emergency_contactname = ?, pat_emergency_contactno = ?, " +
                   "pat_bloodtype = ?, pat_medicalconditions = ?, pat_medications = ?, pat_allergies = ? WHERE pat_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
        
            stmt.setString(1, patient.getContact_number());
            stmt.setString(2, patient.getAddress());
        
            stmt.setString(3, patient.getEmergency_contact_name());
            stmt.setString(4, patient.getEmergency_contact_number());
            stmt.setString(5, patient.getBlood_type());
            stmt.setString(6, patient.getMedical_conditions());
            stmt.setString(7, patient.getMedications());
            stmt.setString(8, patient.getAllergies());
            stmt.setInt(9, patient.getId());
        
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);
            return rowsAffected > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        return false;
        }
    }
   
    //This is for Admin Access side
    public boolean createAccount(Patient patient) {
        try (Connection con = DBConnection.getConnection()){
            String query = "INSERT INTO tblpatientinfo (pat_username, pat_password, pat_fullname, pat_dob , pat_gender, pat_contactnumber, pat_address )VALUES(?,?,?,?,?,?,?) ";
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
    
    public boolean updatePatient(Patient patient){
         String query = "UPDATE tblpatientinfo SET pat_username = ?, pat_password = ?, pat_fullname = ?, pat_dob = ?, pat_gender = ?, pat_contactnumber = ?, pat_address = ? WHERE pat_id = ?";
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
        
         }
        return false;
        
    }
    //Soft Delete (Archive)
    public boolean softdDeletePatient(int id){
        String query = "UPDATE tblpatientinfo SET archived = 1 WHERE pat_id = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
             return false;
       
        }
       
            
    }
    //Restore the Archived Patient
    public boolean restorePatient(int id){
        String query = "UPDATE tblpatientinfo SET archived = 0 WHERE pat_id =?";
        try(Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
       
    }
    //Hard Delete or Permanently delete
    public boolean hardDeletePatient(int id) {
        String query = "DELETE FROM tblpatientinfo WHERE pat_id = ?";
        try(Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
         
    }
    //This will serve as the VIEW CRUD API
    //Get All the Active Patient (Exclude Archive / SoftDeleted)
    public List<Patient> getAllActivePatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM tblpatientinfo WHERE archived = 0";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery() ){
            while(rs.next()){
                Patient patient = mapResultSetToPatient(rs);
                patients.add(patient);
            
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        
        }
        return patients;
    }
    // Get All Archived Patients
    public List<Patient> getArchivedPatients()  {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM tblpatientinfo WHERE archived = 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Patient patient = mapResultSetToPatient(rs);
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
       
        }
        return patients;
    }
    //This will serve as Implementation of View API
    // Helper method to map ResultSet to Patient object
    private Patient mapResultSetToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getInt("pat_id"));
        patient.setUsername(rs.getString("pat_username"));
        patient.setPassword(rs.getString("pat_password"));
        patient.setFull_name(rs.getString("pat_fullname"));
        patient.setDob(rs.getDate("pat_dob") != null ? rs.getDate("pat_dob").toLocalDate() : null);
        patient.setGender(rs.getString("pat_gender"));
        patient.setContact_number(rs.getString("pat_contactnumber"));
        patient.setAddress(rs.getString("pat_address"));
        patient.setEmergency_contact_name(rs.getString("pat_emergency_contactname"));
        patient.setEmergency_contact_number(rs.getString("pat_emergency_contactno"));
        patient.setBlood_type(rs.getString("pat_bloodtype"));
        patient.setMedical_conditions(rs.getString("pat_medicalconditions"));
        patient.setMedications(rs.getString("pat_medications"));
        patient.setAllergies(rs.getString("pat_allergies"));
        return patient;
    }
    
    
    
}
