package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LecturaArchivo {
    List<Partido> partidos = new ArrayList<> ();



    public  void leerArchivo(String s) {
        try {BufferedReader  reader = new BufferedReader ( new FileReader ( "C:\\Users\\PC\\Documents\\Resultados.csv"));
            String linea;
            linea = reader.readLine ();
            while ((linea = reader.readLine ()) != null) {
                String[] partes = linea.split ( ";" );
                imprimirLinea ();
                int id = Integer.parseInt ( partes[0] );
                Equipo equipo1 = new Equipo ( partes[1] );
                int golesEquipo1 = Integer.parseInt ( partes[2] );
                int golesEquipo2 = Integer.parseInt ( partes[3] );
                Equipo equipo2 = new Equipo ( partes[4] );
                Partido partido = new Partido ( id, equipo1, golesEquipo1, golesEquipo2,equipo2 );
                partidos.add ( partido );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog ( null, e );
        }
    }

    public void imprimirLinea() throws RuntimeException {
        String[] partes = new String[0];
        for (int i = 0; i < partes.length; i++) ;
        partes = new String[0];
        System.out.println ( partes[0] + " |  " );

        Ronda ronda = new Ronda ( 1, partidos.toArray (), 50, 1 );

        List<Pronostico> pronosticos = new ArrayList<> ();

        try {
            BufferedReader read = new BufferedReader ( new FileReader ( "C:\\Users\\PC\\Documents\\Pronosticos.csv"));
            String linea;
            linea = read.readLine ();
            while ((linea = read.readLine ()) != null) {
                partes = linea.split ( ";" );
                imprimirLinea ();
                int id = Integer.parseInt ( partes[0] );
                Partido partido = (buscarPartido ( partidos, id ));

                String empata = (partes[3]);
                String gana2 = (partes[4]);
                Resultado resultado = Resultado.ganador;
                assert partido != null;
                Equipo equipo = partido.getEquipo1 ();
                if (empata.equals ( "x" )) {
                    resultado = Resultado.empate;
                }
                if (gana2.equals ( "x" )) {
                    equipo = partido.getEquipo2 ();
                }
                Pronostico pronostico = new Pronostico ( partido, equipo, resultado );
                pronosticos.add ( pronostico );

            }
        } catch (IOException e) {
            e.printStackTrace ();
        }

        for (Partido partido : partidos) {
            System.out.println ( "id " + partido.getId () + "   " + partido.getEquipo1 ().getNombre () + "  " + partido.getGolesEquipo1 () + "  " + partido.getEquipo2 ().getNombre () + "  " + partido.getGolesEquipo2 () );
        }
        int totalpuntos = 0;
        for (Pronostico pronostico : pronosticos) {
            System.out.println ( "id " + pronostico.getPartido ().getId () + "  " + pronostico.getPartido ().getEquipo1 ().getNombre () + " " + pronostico.getResultados () + " " + pronostico.getPartido ().resultados ( pronostico.getEquipo () ) + "\n" );

            totalpuntos = totalpuntos + pronostico.puntos ();
        }
        System.out.println ( "el total es :  " + ronda.getPuntos () * totalpuntos );


    }

    public static Partido buscarPartido(List<Partido> partidos, int id) {
        for (Partido partido : partidos) {
            if (partido.getId () == id) {
                return partido;
            }
        }


        return null;
    }
}