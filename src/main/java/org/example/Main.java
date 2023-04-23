package org.example;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        String Participante;
        //usamos metodo scanner para buscar el nombre del participante

        Scanner Scan = new Scanner ( System.in );
        System.out.print ( " Introduzca nombre de participante  " );
        Participante = Scan.nextLine ();
        //llamamos a la base de Datos
        DataBase conexion = new DataBase ();
        //Declaramos variables que vamos a usar mas adelante
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        ResultSet rs1 = null;


        try {
            try {
                //crea la conxion con la base de datos
                cn = conexion.conectar ();
                stm = cn.createStatement ();

                //lee tabla en  base de pronosticos
                rs1 = stm.executeQuery ( "SELECT COUNT (*) FROM Pronostico  WHERE Participante = " + Participante + " " );
                while (rs1.next ()) {

                    //identificacion de columnas en pronostico
                    Participante = rs1.getString ( 1 );
                    int id = rs1.getInt ( 2 );
                    String Equipo1 = rs1.getString ( 3 );
                    int gana1 = rs1.getInt ( 4 );
                    int empate = rs1.getInt ( 5 );
                    int gana2 = rs1.getInt ( 6 );
                    String Equipo2 = rs1.getString ( 7 );
                    int totalpuntos = 0;

                    //lee tabla Resultado en base de datos
                    rs = stm.executeQuery ( "SELECT * FROM Resultado WHERE Equipo1 = " + Equipo1 + "AND Equipo2 = " + Equipo2 + " " );
                    while (rs.next ()) {

                        //identificación de posicion de columnas
                        int Ronda = rs.getInt ( 1 );
                        id = rs.getInt ( 2 );
                        Equipo1 = rs.getString ( 3 );
                        int CantidadGoles1 = rs.getInt ( 4 );
                        int CantidadGoles2 = rs.getInt ( 5 );
                        Equipo2 = rs.getString ( 6 );


                        //comparacion de resultados para clasificarlos como ganador, perdedor o empatados

                        if (gana1 == 1 && CantidadGoles1 > CantidadGoles2) {

                            Equipo1 = "ganador";
                            totalpuntos++;

                        } else if (gana2 == 1 && CantidadGoles1 < CantidadGoles2) {
                            Equipo1 = "perdedor";
                            totalpuntos++;

                        } else if (empate == 1 && CantidadGoles1 == CantidadGoles2) {
                            Equipo1 = "empate";
                            Equipo2 = "empate";
                            totalpuntos++;
                        }
                        System.out.print ( "total de puntos es:  " );
                        System.out.println ( "El participante  " + Participante+ "obtuvo  " + totalpuntos);

                    }
                }



            } catch (SQLException e) {
                System.out.println ( "ocurrió un error en conex a Base de Datos" );


            } finally {
                try {
                    if (rs != null && rs1 != null) {
                        rs.close ();
                        rs1.close ();
                    }
                    if (stm != null) {
                        stm.close ();
                    }
                    if (cn != null) {
                        cn.close ();
                    }
                } catch (SQLException e) {
                    System.out.println ( "Ocurrió un error al cerrar base de Datos " );
                }


            }
        } finally {

        }
    }


    }

