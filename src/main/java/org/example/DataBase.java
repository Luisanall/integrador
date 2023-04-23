package org.example;

import java.sql.*;

public class DataBase {
    private static final String sURL="jdbc:mysql://db4free.net:3306/lu3576012";
   private static final String Usuario ="luvenejava";
    private static final String contraseña = "L3576012L";
    public Connection conectar() {
        Connection con = null;
        try {
           con = DriverManager.getConnection ( sURL, Usuario, contraseña);
            System.out.println ( " conexion ok" );
        } catch (SQLException e) {
            System.out.println ("Ocurrió un error en conexion");
        }

        return  con;


    }

}