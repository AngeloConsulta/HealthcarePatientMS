/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.dao;

import com.application.model.Patient;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface PatientDAO {
    public Patient getPatientLogin(String username , String password);
    public boolean updatePatientPersonalDetails(Patient patient);
    public boolean createAccount(Patient patient);
    public boolean updatePatient(Patient patient);
    public boolean softdDeletePatient(int id);
    public boolean restorePatient(int id);
    public boolean hardDeletePatient(int id);
    public List<Patient> getAllActivePatients();
    public List<Patient> getArchivedPatients();
}
