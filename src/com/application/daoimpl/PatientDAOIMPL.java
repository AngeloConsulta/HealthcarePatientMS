/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.daoimpl;

/**
 *
 * @author Administrator
 */

import com.application.dao.PatientDAO;
import com.application.model.Patient;
import com.application.util.DBConnection;
import com.application.util.QueryConstant;
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


public class PatientDAOIMPL extends DBConnection implements PatientDAO, QueryConstant{
   

   
    
    @Override
    public Patient getPatientLogin(String username , String password) {
       
        Patient patient = null;

        try {
        connection();
            stmt = con.prepareStatement(PAT_LOGIN);

            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

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
            Logger.getLogger(PatientDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
        }
        return patient;
    }

    public boolean addAppointment(int patientId, int scheduleId) {
        
        boolean success = false;
        String query = "INSERT INTO tblappointment (pat_id, schedule_id) VALUES (?, ?)";

        try {
            connection();
            stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, patientId);
            stmt.setInt(2, scheduleId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int appointmentId = rs.getInt(1);
                    success = updatePatientAppointment(patientId, appointmentId);
                }
            }
        }catch (SQLException e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }finally {
            try { con.close(); } catch (SQLException ex) { System.out.println("Error closing connection: " + ex.getMessage()); }
        }

    return success;
    }
    public boolean updatePatientAppointment(int patientId, int appointmentId) {
               
        boolean success = false;
        String query = "UPDATE tblpatientinfo SET appointment_id = ? WHERE pat_id = ?";
        

        try {
                       
            connection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, appointmentId);
            stmt.setInt(2, patientId);
        
            success = stmt.executeUpdate() > 0;
            
        }catch (SQLException e) {
            System.out.println("Error updating patient appointment: " + e.getMessage());
        }finally {
            try { con.close(); } catch (SQLException ex) { System.out.println("Error closing connection: " + ex.getMessage()); }
        }

        return success;
    }


    @Override
    public boolean updatePatientPersonalDetails(Patient patient) {
    
         try {
         connection();
            stmt = con.prepareStatement(PAT_PATIENTUPDATE);
        
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
    @Override
    public boolean createAccount(Patient patient) {
         try {
         connection();
            stmt = con.prepareStatement(PAT_CREATE);
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
            Logger.getLogger(PatientDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        
        }
   
    }
    
    @Override
    public boolean updatePatient(Patient patient){
         
         try {
        connection();
             stmt = con.prepareStatement(PAT_ADMINUPDATE);
             
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
              Logger.getLogger(PatientDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
        
         }
        return false;
        
    }
    //Soft Delete (Archive)
    @Override
    public boolean softdDeletePatient(int id){
        
         try {
         connection();
            stmt = con.prepareStatement(PAT_ARCHIVE);
           
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            Logger.getLogger(PatientDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
             return false;
       
        }
       
            
    }
    //Restore the Archived Patient
    @Override
    public boolean restorePatient(int id){
        try {
        connection();
            stmt = con.prepareStatement(PAT_RESTORE);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(PatientDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
       
    }

    //Hard Delete or Permanently delete
    @Override
    public boolean hardDeletePatient(int id) {
       
         try {
         connection();
            stmt = con.prepareStatement(PAT_DELETION);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(PatientDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
         
    }
    //This will serve as the VIEW CRUD API
    //Get All the Active Patient (Exclude Archive / SoftDeleted)
    @Override
    public List<Patient> getAllActivePatients() {
        List<Patient> patients = new ArrayList<>();
        try {
        connection();
                stmt = con.prepareStatement(PAT_VIEWACTIVEPATIENT);
                rs = stmt.executeQuery();
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
    @Override
    public List<Patient> getArchivedPatients()  {
        List<Patient> patients = new ArrayList<>();
         try {
         connection();
             stmt = con.prepareStatement(PAT_VIEWARCHIVE);
             rs = stmt.executeQuery();
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
