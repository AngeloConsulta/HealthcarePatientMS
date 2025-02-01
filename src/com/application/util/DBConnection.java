/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.util;

/**
 *
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public abstract class DBConnection {

    private static final String DBName = "dbhpdms";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DBName;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    public Connection con;
    public Statement state;
    public ResultSet rs;
    public PreparedStatement stmt;

    public void connection() {
        try {
//             Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e) {
            System.out.println(e);
        }
       }
}

