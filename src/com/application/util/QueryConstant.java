/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.util;

/**
 *
 * @author Administrator
 */
public interface QueryConstant {
     
    //DOCTOR QUERY
    String DOC_UPDATE = "UPDATE tbldoctorinfo SET doc_contact_number = ?,  doc_address = ? WHERE doc_id = ?";//ADMIN ACCESS FOR UPDATE
    String DOC_LOGIN = "SELECT * FROM tbldoctorinfo WHERE doc_username = ? AND doc_password = ?";//Doctor Login 
    String DOC_CREATEACCOUNT = "INSERT INTO tbldoctorinfo (doc_username, doc_password, doc_fullname, doc_specialization, doc_license_number, doc_contact_number, doc_gender, doc_dob, doc_address )VALUES(?,?,?,?,?,?,?,?,?) "; //ADMIN ACCESS FOR CREATE ACCOUNT
    String DOC_ARCHIVE = "UPDATE tbldoctorinfo SET archived = 1 WHERE doc_id = ?"; //ADMIN ACCESS FOR ARCHIVING DOCTOR DATA
    String DOC_RESTORE = "UPDATE tbldoctorinfo SET archived = 0 WHERE doc_id = ?"; //ADMIN ACCESS FOR RESTORING DOCTORS DATA
    String DOC_PERMANENTDELETE = "DELETE FROM tbldoctorinfo WHERE doc_id = ?";  //ADMIN ACCESS FOR DELETION OF DOCTOR'S DATA
    String DOC_VIEWACTIVEDOCTOR = "SELECT * FROM tbldoctorinfo WHERE archived = 0";  // ADMIN ACCESS FOR VIEW DOCTOR'S DATA
    String DOC_VIEWARCHIVE = "SELECT * FROM tbldoctorinfo WHERE archived = 1";  //ADMIN ACCESS FOR VIEWING THE DOCTOR'S ARCHIVE DATA
    String VIEWAPPOINTMENT = "SELECT app_id, pat_fullname, tblavailableschedule.schedule_id, reason, tblavailableschedule.schedule_date, tblavailableschedule.start_time "
                + "FROM tblpatientinfo "
                + "INNER JOIN tblpatientappointment ON tblpatientappointment.pat_id = tblpatientinfo.pat_id "
                + "INNER JOIN tblavailableschedule ON tblpatientappointment.schedule_id = tblavailableschedule.schedule_id "
                + "INNER JOIN tbldoctorinfo ON tbldoctorinfo.doc_id = tblavailableschedule.doc_id WHERE tblavailableschedule.doc_id = ? ";
    String ADDSCHEDULE = "INSERT INTO tblavailableschedule (schedule_date, start_time, doc_id) VALUES (?, ?, ?)";
    String VIEWAVAILABLESCHED = "SELECT s.schedule_id, s.doc_id, d.doc_fullname,d.doc_specialization, s.schedule_date, s.start_time " +
                   "FROM tblavailableschedule s " +
                   "JOIN tbldoctorinfo d ON s.doc_id = d.doc_id " +
                   "WHERE s.toggle = 0";
    
    
    
    
   //PATIENT QUERY
    String PAT_LOGIN = "SELECT * FROM tblpatientinfo WHERE pat_username = ? AND pat_password = ?";
    String PAT_PATIENTUPDATE = "UPDATE tblpatientinfo SET  pat_contactnumber = ?, pat_address = ?, pat_emergency_contactname = ?, pat_emergency_contactno = ?, " +
                   "pat_bloodtype = ?, pat_medicalconditions = ?, pat_medications = ?, pat_allergies = ? WHERE pat_id = ?";
    String PAT_CREATE = "INSERT INTO tblpatientinfo (pat_username, pat_password, pat_fullname, pat_dob , pat_gender, pat_contactnumber, pat_address )VALUES(?,?,?,?,?,?,?) ";
    String PAT_ADMINUPDATE = "UPDATE tblpatientinfo SET pat_username = ?, pat_password = ?, pat_fullname = ?, pat_dob = ?, pat_gender = ?, pat_contactnumber = ?, pat_address = ? WHERE pat_id = ?";
    String PAT_ARCHIVE = "UPDATE tblpatientinfo SET archived = 1 WHERE pat_id = ?";
    String PAT_RESTORE = "UPDATE tblpatientinfo SET archived = 0 WHERE pat_id =?";
    String PAT_DELETION = "DELETE FROM tblpatientinfo WHERE pat_id = ?";
    String PAT_VIEWACTIVEPATIENT = "SELECT * FROM tblpatientinfo WHERE archived = 0";
    String PAT_VIEWARCHIVE = "SELECT * FROM tblpatientinfo WHERE archived = 1";
    String ADDAPPOINTMENT = "INSERT INTO tblpatientappointment (schedule_id, reason, pat_id) VALUES (?, ?, ?)";//Booking appointment
    String UPDATEAPPOINTMENT = "UPDATE tblavailableschedule SET toggle = 1 WHERE schedule_id = ?";//Booking appointment

      
    
    //ADMIN 
    String ADMIN_LOGIN = "SELECT * FROM tbladmininfo WHERE admin_username =? AND admin_password =? ";
    String ADMIN_CREATEACCOUNT = "INSERT INTO tbladmininfo (admin_username, admin_password, admin_fullname, admin_age, admin_gender)VALUES(?,?,?,?,?) ";
    String SEARCHDOCTOR = "SELECT 'Doctor' AS type, doc_id, doc_username, doc_fullname, doc_specialization FROM tbldoctorinfo WHERE doc_fullname LIKE ? OR doc_username LIKE ?";
    String SEARCHPATIENT = "SELECT 'Patient' AS type, pat_id, pat_username, pat_fullname, pat_medicalconditions FROM tblpatientinfo WHERE pat_fullname LIKE ? OR pat_username LIKE ?";
}
