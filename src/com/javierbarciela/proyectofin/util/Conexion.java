/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javierbarciela.proyectofin.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iñigo
 */
public abstract class Conexion {
    private static Connection cnn = null;
    
    public static Connection getConexion(){
        if (cnn != null) return cnn;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            cnn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:ORCL",
                "scott",
                "tiger");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnn;
    }
    
    public static void cerrarConexion(){
        try { cnn.close(); } catch (SQLException ex){}
    }
}
