/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.model;

import java.time.LocalDate;

/**
 *
 * @author Administrator
 */
public class Doctor {
    private int id;
    private String username;
    private String password;
    private String name;
    private String specialization;
    private String contactNumber;
    private String address;
    private String licenseNumber;
    private String gender;
    private LocalDate DOB;
    private LocalDate availabilityStatus;

    public Doctor(String username, String password, String name, String specialization, String contactNumber, String address, String licenseNumber, String gender, LocalDate DOB) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.specialization = specialization;
            this.contactNumber = contactNumber;
            this.address = address;
            this.licenseNumber = licenseNumber;
            this.gender = gender;
            this.DOB = DOB;        

    
    }

    public Doctor(int id, String username, String password, String name, String specialization, String contactNumber, String address, String licenseNumber, String gender, LocalDate DOB, LocalDate availabilityStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.address = address;
        this.licenseNumber = licenseNumber;
        this.gender = gender;
        this.DOB = DOB;
        this.availabilityStatus = availabilityStatus;
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

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    

    public String getSpecialization() {
        return specialization;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

//    @Override
//    public String toString() {
//        return "Doctor{" + "id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", specialization=" + specialization + ", contactNumber=" + contactNumber + ", address=" + address + ", licenseNumber=" + licenseNumber + ", gender=" + gender + ", DOB=" + DOB + '}';
//    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s %-20s %-20s %-15s %-10s %-15s %-30s",
        username, name, specialization, licenseNumber, contactNumber, gender, DOB, address);
    }
  
}
