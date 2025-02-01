/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.daoimpl;

import com.application.dao.DoctorDAO;
import com.application.model.Doctor;

import com.application.util.DBConnection;
import com.application.util.QueryConstant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DoctorDAOIMPL extends DBConnection implements DoctorDAO,  QueryConstant {

     //This method is only accessible in Doctor's: This is needed to M
     @Override
     public Doctor getDoctorLogin(String username , String password) {
       
        Doctor doctor = null;
        try {
        connection();     
            stmt = con.prepareStatement(DOC_LOGIN);
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

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
                }

        } catch (SQLException e) {
            Logger.getLogger(DoctorDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
        }
        return doctor;
    }  
     


   
    public ArrayList<Doctor> fetchSched() {
        String query = "SELECT tbldoctor.doc_id, name, specialization, schedule_id, schedule_date, start_time FROM tbldoctor INNER JOIN tblavailableschedule ON tbldoctor.doc_id = tblavailableschedule.doc_id WHERE toggle = 0";
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            connection();
            state = con.createStatement();
            rs = state.executeQuery(query);

            while (rs.next()) {
                doctors.add(new Doctor(
                       rs.getInt("schedule_id"),
                       rs.getString("name"),
                       rs.getString("specialization"),
                       rs.getDate("schedule_date"),
                       rs.getTime("start_time"),
                       rs.getInt("doctor_id")
                ));
            }

        } catch (SQLException e) {
            System.out.println("DoctorDaoImpl: fetchSched() " + e.getMessage());

        } finally {
            try {
                con.close();
                state.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Failed to close resources " + e.getMessage());
            }
        }
        return doctors;
    }

    public List<Doctor> getAvailableSchedules() {
    List<Doctor> schedules = new ArrayList<>();
    String query = "SELECT s.schedule_id, s.doc_id, d.doc_fullname, s.schedule_date, s.start_time " +
                   "FROM tblavailableschedule s " +
                   "JOIN tbldoctor d ON s.doc_id = d.doc_id " +
                   "WHERE s.toggle = 0";

    try {
        connection();
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Doctor doctor = new Doctor();
            doctor.setSchedule_id(rs.getInt("schedule_id"));
            doctor.setId(rs.getInt("doc_id"));
            doctor.setName(rs.getString("doc_fullname"));
            doctor.setSchedDate(rs.getDate("schedule_date"));
            doctor.setSchedTime(rs.getTime("start_time"));

            schedules.add(doctor);
        }
    } catch (SQLException e) {
        System.out.println("Error fetching available schedules: " + e.getMessage());
    } finally {
        try { con.close(); } catch (SQLException ex) { /* Handle close error */ }
    }

    return schedules;
     }
    
    public boolean addSchedule(Doctor doctor) {
        boolean addSuccess = false;
        String query = "INSERT INTO tblavailableschedule (schedule_date, start_time, doc_id) VALUES (?, ?, ?)";

        try {
            connection();
            stmt = con.prepareStatement(query);
            stmt.setDate(1, (java.sql.Date) doctor.getSchedDate());
            stmt.setTime(2, (java.sql.Time) doctor.getSchedTime());
            stmt.setInt(3, doctor.getId());
            int rowsAffected = stmt.executeUpdate();
            addSuccess = rowsAffected > 0;
    
        } catch (SQLException e) {
            System.out.println("Error Adding Schedule" + doctor.getName() + " - " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Failed to close resources: " + ex.getMessage());
            }
        }

        return addSuccess;

    }
    
     @Override
    public boolean createAccount(Doctor doctor){ //This is for Creation of record by Admin
         try {
         connection();        
            stmt = con.prepareStatement(DOC_CREATEACCOUNT);
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
            Logger.getLogger(DoctorDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
     }

     @Override
     public boolean updateDoctor(Doctor doctor){
            try {
           connection(); 
               stmt = con.prepareStatement(DOC_UPDATE);
               stmt.setString(1, doctor.getUsername());
               stmt.setString(2, doctor.getName());
               stmt.setString(3, doctor.getSpecialization());
               stmt.setString(4, doctor.getLicenseNumber());
               stmt.setString(5, doctor.getContactNumber());
               stmt.setString(6, doctor.getGender());
               stmt.setDate(7, doctor.getDOB()!=null? java.sql.Date.valueOf(doctor.getDOB()): null);
               stmt.setString(8, doctor.getAddress());
               stmt.setInt(9, doctor.getId());
               int rowsAffected = stmt.executeUpdate();
               return rowsAffected > 0;
           }catch(SQLException e){
               e.printStackTrace();
           }
         return false;
     }
     //Soft Delete (Archive)
     @Override
     public boolean softdDeleteDoctor(int id){
 
        try {
        connection(); 
            stmt = con.prepareStatement(DOC_ARCHIVE);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            Logger.getLogger(DoctorDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
            
    }
    //Restore the Archived Patient
     @Override
    public boolean restoreDoctor(int id){
        
        try {
        connection(); 
            state = con.prepareStatement(DOC_RESTORE);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    //Hard Delete or Permanently delete
     @Override
    public boolean hardDeleteDoctor(int id){
   
        try {
        connection(); 
            stmt = con.prepareStatement(DOC_PERMANENTDELETE);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAOIMPL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    //This will serve as the VIEW CRUD API
    //Get All the Active Doctor (Exclude Archive / SoftDeleted)
     @Override
    public List<Doctor> getAllActiveDoctor(){
        List<Doctor> doctors = new ArrayList<>();
        try{
        connection(); 
                stmt = con.prepareStatement(DOC_VIEWACTIVEDOCTOR );
                rs = stmt.executeQuery();
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
     @Override
    public List<Doctor> getArchivedDoctor() {
        List<Doctor> doctors = new ArrayList<>();
         try{
         connection(); 
            stmt = con.prepareStatement(DOC_VIEWARCHIVE);
            rs = stmt.executeQuery();
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
        
        return doctor;
    }

     
}
