package org.example;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


      public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
          // ingresamos valor del punto en pantalla
          Scanner Scan = new Scanner ( System.in );
          System.out.print ( " Introduzca valor del punto" );
          int puntajePorAcierto = Scan.nextInt ();

          //declaramos variables que vamos a usar mas adelante

          DataBase conexion = new DataBase ();
          Connection cn = null;
          Statement stm = null;
          ResultSet rs = null;
          int totalpuntos = 0;
          ArrayList<Partido> partidos;
          partidos = null;

          try {
              cn = conexion.conectar ();
              stm = cn.createStatement ();

              partidos = new ArrayList<> ();

              {
                  try {
                      Scanner Sc = new Scanner ( System.in );
                      System.out.println ("ingrese nombre del participante ");
                       Sc.nextLine ();
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
                              //comparacion de resultados para clasificarlos como ganador, perdedor o empatadosº
                              // al comparar se le asigna punto por aciertos
                              if (gana1 == 1 && CantidadGoles1 > CantidadGoles2) {
                                  Equipo1 = "acertado";
                                  totalpuntos += puntajePorAcierto;

                              } else if (gana2 == 1 && CantidadGoles1 < CantidadGoles2) {
                                  Equipo1 = "acertado";
                                  totalpuntos += puntajePorAcierto;

                              } else if (empate == 1 && CantidadGoles1 == CantidadGoles2) {
                                  Equipo1 = "empate";
                                  Equipo2 = "empate";
                                  totalpuntos += puntajePorAcierto;
                              }
                          }
                      }
                  } catch (SQLException ex) {
                      throw new RuntimeException ( ex );
                  }

              }
              Ronda ronda = new Ronda ( 1, partidos.toArray (), 50, 1 );

              List<Pronostico> pronosticos = new ArrayList<> ();

              for (Pronostico pronostico : pronosticos) {
                  System.out.println ( "id " + pronostico.getParticipante () + "  " + pronostico.getPartido ().getId () + "  " + pronostico.getPartido ().getEquipo2 () + " " + pronostico.getResultados () + " " + pronostico.getPartido ().resultados ( pronostico.getEquipo () ) + "\n" );

                  totalpuntos = totalpuntos + pronostico.puntos ();
              }
              System.out.println ( totalpuntos * puntajePorAcierto );


          } catch (SQLException e) {
              System.out.println ( "ocurrió un error en conex a Base de Datos" );

              Ronda ronda = new Ronda ( 1, partidos.toArray (), 50, 1 );


              List<Pronostico> pronostico = new ArrayList<> ();


              for (Partido partido : partidos) {
                  System.out.println ( "id " + ronda.getId () + "   " + partido.getId () + "   " + partido.getEquipo1 () + "  " + partido.getCantidadGoles1 () + "  " + partido.getEquipo2 () + "  " + partido.getCantidadGoles2 () );
              }
              for (Pronostico prono : pronostico) {
                  System.out.println ( "id " + prono.getParticipante () + "  " + prono.getPartido ().getId () + "  " + prono.getPartido ().getEquipo2 () + " " + prono.getResultados () + " " + prono.getPartido ().resultados ( prono.getEquipo () ) + "\n" );

                  totalpuntos = totalpuntos + prono.puntos ();
              }
              System.out.print ( "El participante  obtuvo " + totalpuntos + " puntos por este partido. " );

              System.out.println ( "El puntaje por acierto fue de " + puntajePorAcierto );
              if (totalpuntos * puntajePorAcierto > 10);
              

              System.out.println (" a este participante se le agregó puntos extras");


          } finally {

                  if (cn != null) {
                      try {
                          cn.close ();
                          if (rs != null) {
                              rs.close ();
                              Scan.close ();
                              System.out.print ( "Hubo error al cerrar base de Datos" );
                          }

                      } catch (SQLException e) {
                          throw new RuntimeException ( e );
                      }
                  }
              }


          }
      }

