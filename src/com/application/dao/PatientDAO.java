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
        String query = "SELECT * FROM patient WHERE username = ? AND password = ?";
        Patient patient = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient();
                    patient.setId(rs.getInt("patient_id"));
                    patient.setUsername(rs.getString("username"));
                    patient.setPassword(rs.getString("password"));
                    patient.setFull_name(rs.getString("full_name"));
                    
                    patient.setDob(rs.getDate("dob").toLocalDate());
                    patient.setGender(rs.getString("gender"));
                    patient.setContact_number(rs.getString("contact_number"));
                    patient.setAddress(rs.getString("address"));
                    patient.setEmergency_contact_name(rs.getString("emergency_contact_name"));
                    patient.setEmergency_contact_number(rs.getString("emergency_contact_number"));
                    patient.setBlood_type(rs.getString("blood_type"));
                    patient.setMedical_conditions(rs.getString("medical_conditions"));
                    patient.setMedications(rs.getString("medications"));
                    patient.setAllergies(rs.getString("allergies"));
                    
                
            }

        } catch (SQLException e) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return patient;
    }

    
    public boolean updatePatientPersonalDetails(Patient patient) {
    String query = "UPDATE patient SET  contact_number = ?, address = ?, emergency_contact_name = ?, emergency_contact_number = ?, " +
                   "blood_type = ?, medical_conditions = ?, medications = ?, allergies = ? WHERE patient_id = ?";
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
    public boolean bookAppointment(int patientId, int doctorId, String appointmentDate, String reason) {
        String query = "INSERT INTO appointment (patient_id, doctor_id, appointment_date, reason) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setString(3, appointmentDate);
            stmt.setString(4, reason);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     public List<String> getAvailableDoctors() {
        List<String> doctors = new ArrayList<>();
        String query = "SELECT doctor_id, name, specialization FROM doctor WHERE availabilityStatus IS NOT NULL";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                doctors.add("Doctor ID: " + rs.getInt("doctor_id") + 
                            ", Name: " + rs.getString("name") + 
                            ", Specialization: " + rs.getString("specialization"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
    
    //This is for Admin Access side
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
    //Soft Delete (Archive)
    public boolean softdDeletePatient(int id){
        String query = "UPDATE patient SET archived = 1 WHERE patient_id = ?";
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
        String query = "UPDATE patient SET archived = 0 WHERE patient_id =?";
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
    public boolean hardDeletePatient(int id){
        String query = "DELETE FROM patient WHERE patient_id = ?";
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
    public List<Patient> getAllActivePatients(){
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient WHERE archived = 0";
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
    public List<Patient> getArchivedPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient WHERE archived = 1";
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
        patient.setId(rs.getInt("patient_id"));
        patient.setUsername(rs.getString("username"));
        patient.setPassword(rs.getString("password"));
        patient.setFull_name(rs.getString("full_name"));
        patient.setDob(rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null);
        patient.setGender(rs.getString("gender"));
        patient.setContact_number(rs.getString("contact_number"));
        patient.setAddress(rs.getString("address"));
        patient.setEmergency_contact_name(rs.getString("emergency_contact_name"));
        patient.setEmergency_contact_number(rs.getString("emergency_contact_number"));
        patient.setBlood_type(rs.getString("blood_type"));
        patient.setMedical_conditions(rs.getString("medical_conditions"));
        patient.setMedications(rs.getString("medications"));
        patient.setAllergies(rs.getString("allergies"));
        return patient;
    }
    
    
    
}
