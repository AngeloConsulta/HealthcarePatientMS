/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.model;

/**
 *
 * @author Administrator
 */
import java.time.LocalDate;
public class Patient {
    private int id;
    private String username;	  //*tbluserinfos //*tblpatientinfo
    private String password;	//*tbluserinfos
    private String first_name;	//*tbluserinfos
    private String last_name;	//*tbluserinfos
    private String middle_name;	//*tbluserinfos
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

    public Patient(String username, String password, String first_name, String last_name, String middle_name, LocalDate dob, String gender, String contact_number, String address) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.dob = dob;
        this.gender = gender;
        this.contact_number = contact_number;
        this.address = address;
    }

    public Patient(int id, String username, String password, String first_name, String last_name, String middle_name, LocalDate dob, String gender, String contact_number, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.dob = dob;
        this.gender = gender;
        this.contact_number = contact_number;
        this.address = address;
    }

    public int getId() {
        return id;
    }
   
    
   

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getAddress() {
        return address;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public String getEmergency_contact_number() {
        return emergency_contact_number;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public String getMedical_conditions() {
        return medical_conditions;
    }

    public String getMedications() {
        return medications;
    }

    public String getAllergies() {
        return allergies;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", username=" + username + ", password=" + password + ", first_name=" + first_name + ", last_name=" + last_name + ", middle_name=" + middle_name + ", dob=" + dob + ", gender=" + gender + ", contact_number=" + contact_number + ", address=" + address + '}';
    }
    

  
    
}
