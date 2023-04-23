package org.example;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Resultado.ganador;

public class Main {


    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        Scanner Scan = new Scanner ( System.in );
        System.out.print ( " Introduzca nombre de participante  " );

        DataBase conexion = new DataBase ();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            cn = conexion.conectar ();
            stm = cn.createStatement ();
            ArrayList<Partido> partidos = new ArrayList<> ();

            {
                try {
                    //lee tabla Resultado en base de datos
                    rs = stm.executeQuery ( "SELECT* FROM Resultado" );
                    while (rs.next ()) {
                        //identificación de posicion de columnas
                        int Ronda = rs.getInt ( 1 );
                        int id = rs.getInt ( 2 );
                        String Equipo1 = rs.getString ( 3 );
                        int CantidadGoles1 = rs.getInt ( 4 );
                        int CantidadGoles2 = rs.getInt ( 5 );
                        String Equipo2 = rs.getString ( 6 );

                        Partido partido = new Partido ( Ronda, id, Equipo1, CantidadGoles1, CantidadGoles2, Equipo2 );
                        partidos.add ( partido );
                        //lee tabla en  base de pronosticos

                        rs = stm.executeQuery ( "SELECT * FROM Pronostico " );
                        while (rs.next ()) {
                            //identificacion de columnas en pronostico
                            String Participante = rs.getString ( 1 );
                            id = rs.getInt ( 2 );
                            Equipo1 = rs.getString ( 3 );
                            int gana1 = rs.getInt ( 4 );
                            int empate = rs.getInt ( 5 );
                            int gana2 = rs.getInt ( 6 );
                            Equipo2 = rs.getString ( 7 );
                            //comparacion de resultados para clasificarlos como ganador, perdedor o empatados
                            Resultado resultado = ganador;
                            if (partido != null) {
                                if (CantidadGoles1 < CantidadGoles2) {
                                    Equipo1 = String.valueOf ( Resultado.ganador );
                                    gana1 = 1;
                                    if (CantidadGoles1 > CantidadGoles2) {
                                        Equipo1 = String.valueOf ( Resultado.perdedor );
                                        gana2 = 1;
                                        if (CantidadGoles1 == CantidadGoles2) {
                                            Equipo1 = String.valueOf ( Resultado.empate );
                                            Equipo2 = String.valueOf ( Resultado.empate );
                                            empate = 1;
                                        }
                                    }
                                }
                            }

                        }
                    }
                    Ronda ronda = new Ronda ( 1, partidos.toArray (), 50, 1 );

                    List<Pronostico> pronosticos = new ArrayList<> ();


                    for (Partido partido : partidos) {
                        System.out.println ( "id " + ronda.getId () + "   " + partido.getId () + "   " + partido.getEquipo1 () + "  " + partido.getCantidadGoles1 () + "  " + partido.getEquipo2 () + "  " + partido.getCantidadGoles2 () );
                    }
                    int totalpuntos = 0;
                    for (Pronostico pronostico : pronosticos) {
                        System.out.println ( "id " + pronostico.getParticipante () + "  " + pronostico.getPartido ().getId () + "  " + pronostico.getPartido ().getEquipo2 () + " " + pronostico.getResultados () + " " + pronostico.getPartido ().resultados ( pronostico.getEquipo () ) + "\n" );

                        totalpuntos = totalpuntos + pronostico.puntos ();
                    }
                    System.out.println ( "el total es :  " + ronda.getPuntos () * totalpuntos );


                } catch (SQLException e) {
                    System.out.println ( "ocurrió un error en conex a Base de Datos" );

                }


            }


        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }

    }
    }







