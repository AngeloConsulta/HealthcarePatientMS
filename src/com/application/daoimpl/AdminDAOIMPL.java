/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.daoimpl;

import com.application.dao.AdminDAO;
import com.application.model.Admin;
import com.application.util.DBConnection;
import com.application.util.QueryConstant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AdminDAOIMPL extends DBConnection implements AdminDAO, QueryConstant {

    
    @Override
    public boolean verifyCredentials(Admin admin){
        try {
        connection();
            stmt = con.prepareStatement(ADMIN_LOGIN);
            stmt.setString( 1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
//            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
       
    }


    @Override
    public List<Object[]> searchPerson(String keyword){
        List<Object[]> results = new ArrayList<>();
        
        
        try {
        connection();
              PreparedStatement stmtDoctor = con.prepareStatement(SEARCHDOCTOR);
              PreparedStatement stmtPatient = con.prepareStatement(SEARCHPATIENT);

            //  Set parameters for both queries
            stmtDoctor.setString(1, "%" + keyword + "%");
            stmtDoctor.setString(2, "%" + keyword + "%");
            stmtPatient.setString(1, "%" + keyword + "%");
            stmtPatient.setString(2, "%" + keyword + "%");

            // 🔹 Execute Doctor Query
            ResultSet rsDoctor = stmtDoctor.executeQuery();
            while (rsDoctor.next()) {
                results.add(new Object[]{
                        rsDoctor.getString("type"),
                        rsDoctor.getInt("doc_id"),
                        rsDoctor.getString("doc_username"),
                        rsDoctor.getString("doc_fullname"),
                        rsDoctor.getString("doc_specialization")
                });
            }

            // 🔹 Execute Patient Query
            ResultSet rsPatient = stmtPatient.executeQuery();
            while (rsPatient.next()) {
                results.add(new Object[]{
                        rsPatient.getString("type"),
                        rsPatient.getInt("pat_id"),
                        rsPatient.getString("pat_username"),
                        rsPatient.getString("pat_fullname"),
                        rsPatient.getString("pat_medicalconditions")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

        

    
}
