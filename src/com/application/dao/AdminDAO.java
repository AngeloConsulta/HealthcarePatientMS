/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.dao;

import com.application.model.Admin;
import com.application.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AdminDAO{

    
    public boolean verifyCredentials(Admin admin){
        try (Connection con = DBConnection.getConnection()){
            String query = "SELECT * FROM admins WHERE username =? AND password =? ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString( 1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
//            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
       
    }
    public boolean createAccount(Admin admin){
        try (Connection con = DBConnection.getConnection()){
            String query = "INSERT INTO admins (username, password, name, age, gender)VALUES(?,?,?,?,?) ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getName());
            stmt.setInt(4, admin.getAge());
            stmt.setString(5, admin.getGender());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    
           
    }
    
}
