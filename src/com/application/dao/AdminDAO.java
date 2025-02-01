/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.dao;

import com.application.model.Admin;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface AdminDAO {
     public boolean verifyCredentials(Admin admin);
     public List<Object[]> searchPerson(String keyword);
    
}
