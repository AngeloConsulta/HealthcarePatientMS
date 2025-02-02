/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;


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
    private Date schedDate;
    private Time schedTime;
    private int schedule_id;
    private int app_id;
    private int toggle;
    private int pat_id;
    private String pat_name;
    private String reason;
    

    public Doctor(int id, String name, Date schedDate, Time schedTime) {
        this.id = id;
        this.name = name;
        this.schedDate = schedDate;
        this.schedTime = schedTime;
    }

        
    public Doctor(int id, Date schedDate, Time schedTime) {
            this.id = id;
            this.schedDate = schedDate;
            this.schedTime = schedTime;
    }

    public Doctor(int id, String name, String specialization, Date schedDate, Time schedTime, int schedule_id) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.schedDate = schedDate;
        this.schedTime = schedTime;
        this.schedule_id = schedule_id;
    }
    
    
    
    

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

    public Doctor(int app_id, String pat_name, Date schedDate, Time schedTime, String reason) {
        this.app_id = app_id;
        this.pat_name = pat_name;
        this.schedDate = schedDate;
        this.schedTime = schedTime;
        this.reason = reason;
        
       
        
    }
        
    public Doctor() {
    }
    

    public Doctor(int id, String username, String password, String name, String specialization, String contactNumber, String address, String licenseNumber, String gender, LocalDate DOB) {
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

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getToggle() {
        return toggle;
    }

    public void setToggle(int toggle) {
        this.toggle = toggle;
    }

    public int getPat_id() {
        return pat_id;
    }

    public void setPat_id(int pat_id) {
        this.pat_id = pat_id;
    }

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }
    
   
    

    @Override
    public String toString() {
        return String.format("%-5s %-15s %-15s %-20s %-20s %-20s %-15s %-10s %-15s %-30s",
        id,username, name, specialization, licenseNumber, contactNumber, gender, DOB, address);
    }
  
}
