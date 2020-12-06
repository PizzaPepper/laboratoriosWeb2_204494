/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eliu
 */
public class Conexion {

    private static Connection conn = null;
    private  String URL = "jdbc:mysql://localhost:3306/laboratorio?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private String DRIVER = "com.mysql.jdbc.Driver";
    private String USER = "root";
    private String PASSWORD = "1234";

    private Conexion() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
//        if (conn == null) {
            new Conexion();
//        }
            
        return conn;

    }

}
