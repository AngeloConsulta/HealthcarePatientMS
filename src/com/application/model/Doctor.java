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

    public Doctor() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public LocalDate getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(LocalDate availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
    

    @Override
    public String toString() {
        return String.format("%-5s %-15s %-15s %-20s %-20s %-20s %-15s %-10s %-15s %-30s",
        id,username, name, specialization, licenseNumber, contactNumber, gender, DOB, address);
    }
  
}
