/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.model;

/**
 *
 * @author Administrator
 */
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
public class Patient {
    private int id;
    private String username;	  //*tbluserinfos //*tblpatientinfo
    private String password;	//*tbluserinfos
    private String full_name;	//*tbluserinfos
    private LocalDate dob;	//*tbluserinfos
    private String gender;	//*tbluserinfos
    private String contact_number;	     //*tbluserinfos	
    private String address;	//*tbluserinfos
    private String emergency_contact_name;	//*tblpatientinfo
    private String emergency_contact_number;	//*tblpatientinfo
    private String blood_type;	             //*tblpatientinfo
    private String medical_conditions;	       //*tblmedicalhistory
    private String medications;	          //*tblmedicalhistory
    private String allergies;	       //*tblmedicalhistory
   
    private Date schedDate;
    private Time schedTime;
    private int sched_id;
    private String reason;
    private String doc_name;
     private String doc_specialization;
    private int app_id;

    public Patient(int sched_id, String reason) {
        this.sched_id = sched_id;
        this.reason = reason;
    }
    
    public Patient(int id, int app_id, int sched_id, Date schedDate, Time schedTime, String reason, String doc_name, String doc_specialization) {
        this.id = id;
        this.schedDate = schedDate;
        this.sched_id = sched_id;
        this.schedTime = schedTime;
        this.reason = reason;
        this.doc_name = doc_name;
        this.doc_specialization = doc_specialization;
        this.app_id = app_id;
    }

    public Patient(int id, int sched_id, String reason) {
        this.id = id;
        this.sched_id = sched_id;
        this.reason = reason;
    }

    public Date getSchedDate() {
        return schedDate;
    }

    public void setSchedDate(Date schedDate) {
        this.schedDate = schedDate;
    }

    public Time getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(Time schedTime) {
        this.schedTime = schedTime;
    }

    public int getSched_id() {
        return sched_id;
    }

    public void setSched_id(int sched_id) {
        this.sched_id = sched_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_specialization() {
        return doc_specialization;
    }

    public void setDoc_specialization(String doc_specialization) {
        this.doc_specialization = doc_specialization;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }
    
    public Patient() {
    }

    public Patient(int id, String username, String password, String full_name, LocalDate dob, String gender, String contact_number, String address, String emergency_contact_name, String emergency_contact_number, String blood_type, String medical_conditions, String medications, String allergies) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.dob = dob;
        this.gender = gender;
        this.contact_number = contact_number;
        this.address = address;
        this.emergency_contact_name = emergency_contact_name;
        this.emergency_contact_number = emergency_contact_number;
        this.blood_type = blood_type;
        this.medical_conditions = medical_conditions;
        this.medications = medications;
        this.allergies = allergies;
    }

    
    public Patient(String username, String password, String full_name, LocalDate dob, String gender, String contact_number, String address) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.dob = dob;
        this.gender = gender;
        this.contact_number = contact_number;
        this.address = address;
    }

    public Patient(int id, String username, String password, String first_name, String last_name, String middle_name, LocalDate dob, String gender, String contact_number, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.dob = dob;
        this.gender = gender;
        this.contact_number = contact_number;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public void setEmergency_contact_name(String emergency_contact_name) {
        this.emergency_contact_name = emergency_contact_name;
    }

    public String getEmergency_contact_number() {
        return emergency_contact_number;
    }

    public void setEmergency_contact_number(String emergency_contact_number) {
        this.emergency_contact_number = emergency_contact_number;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getMedical_conditions() {
        return medical_conditions;
    }

    public void setMedical_conditions(String medical_conditions) {
        this.medical_conditions = medical_conditions;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    
  
    

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", username=" + username + ", password=" + password + ", full_name=" + full_name + ", dob=" + dob + ", gender=" + gender + ", contact_number=" + contact_number + ", address=" + address + ", emergency_contact_name=" + emergency_contact_name + ", emergency_contact_number=" + emergency_contact_number + ", blood_type=" + blood_type + ", medical_conditions=" + medical_conditions + ", medications=" + medications + ", allergies=" + allergies + '}';
    }  
    
}
