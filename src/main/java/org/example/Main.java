package org.example;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        DataBase conexion = new DataBase ();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            cn = conexion.conectar ();
            stm = cn.createStatement ();
            //lee tabla pronostico en base de Datos
          rs = stm.executeQuery ( "SELECT * FROM Pronostico " );
          while (rs.next ()){
              String Participante = rs.getString ( 1 );
              int id = rs.getInt ( 2 );
              String Equipo1 = rs.getString ( 3 );
              int gana1 = rs.getInt ( 4 );
              int empate = rs.getInt ( 5 );
              int gana2 = rs.getInt ( 6 );
              String Equipo2 = rs.getString ( 7 );
              //lee tabla resultado en base de datos
            rs = stm.executeQuery ( "SELECT* FROM Resultado" );
            while (rs.next ()){
                int Ronda = rs.getInt ( 1 );
                rs.getInt ( 2 );
                rs.getString ( 3 );
                int CantidadGoles1 = rs.getInt ( 4 );
                int CantidadGoles2 = rs.getInt ( 5 );
                rs.getString ( 6 );
                System.out.println (Participante.length ()+ CantidadGoles1 );
            }

              }


        } catch (SQLException e) {
            System.out.println ("ocurrió un error en conex a Base de Datos");


        }


    }

}




