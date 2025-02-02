/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.dao;

import com.application.model.Doctor;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface DoctorDAO {
    public Doctor getDoctorLogin(String username , String password);
    public boolean createAccount(Doctor doctor);
    public boolean updateDoctor(Doctor doctor);//To be edit
    public boolean softdDeleteDoctor(int id);
    public boolean restoreDoctor(int id);
    public boolean hardDeleteDoctor(int id);
    public List<Doctor> getAllActiveDoctor();
    public List<Doctor> getArchivedDoctor();
//    public ArrayList<Doctor> fetchSched();
    public List<Doctor> getAvailableSchedules();
    public boolean addSchedule(Doctor doctor);
    public ArrayList<Doctor> fetchBookedAppointment(Doctor doctor) ;
}
