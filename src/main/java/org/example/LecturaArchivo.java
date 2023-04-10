package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LecturaArchivo {
    @Getter
    @Setter
    private BufferedReader lector;
    @Getter
    @Setter
    private String linea;
    @Getter
    @Setter
    private String partes[] = null;
    List<Partido> partidos = new ArrayList<> ();



    public void leerArchivo() {
        try {BufferedReader  reader = new BufferedReader ( new FileReader ( "C:\\Users\\PC\\Downloads\\resultados(1).csv\\" ) );
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
        for (int i = 0; i < partes.length; i++) ;
        System.out.println ( partes[0] + " |  " );

        Ronda ronda = new Ronda ( 1, partidos.toArray (), 50, 1 );

        List<Pronostico> pronosticos = new ArrayList<> ();

        try {BufferedReader read = new BufferedReader ( new FileReader ("C:\\Users\\PC\\Downloads\\pronostico(1).csv\\"  ) );
            String linea;
            linea = read.readLine ();
            while ((linea = read.readLine ()) != null) {
                String[] partes = linea.split ( ";" );
                imprimirLinea ();
                int id = Integer.parseInt ( partes[0] );
                Partido partido = (buscarPartido ( partidos, id ));

                String empate = (partes[3]);
                String ganados = (partes[4]);
                Resultado resultado = Resultado.ganador;
                Equipo equipo = partido.getEquipo1 ();
                if (empate == "x") {
                    resultado = Resultado.empate;
                }
                if (ganados == "x") {
                    equipo = partido.getEquipo2 ();
                }
                Pronostico pronostico = new Pronostico ( partido,equipo,resultado );
                pronosticos.add ( pronostico );

            }
        } catch (IOException e) {
           e.printStackTrace ();
        }

        for (Partido partido : partidos) {
            System.out.println ( "id " + partido.getId () + "   " + partido.getEquipo1 ().getNombre () + "  " + partido.getGolesEquipo1 () + "  " + partido.getEquipo2 ().getNombre () + "  " + partido.getGolesEquipo2 () );
        }
        int totalpuntos=0;
        for (Pronostico pronostico : pronosticos) {
            System.out.println ("id "+ pronostico.getPartido ().getId ()+ "  "+ pronostico.getPartido ().getEquipo1 ().getNombre ()+ " " + pronostico.getResultados ()+ " "+pronostico.getPartido ().resultados ( pronostico.getEquipo ())+ "\n" );

            totalpuntos = totalpuntos + pronostico.puntos ();
        }
        System.out.println ("el total es :  "+ ronda.getPuntos ()* totalpuntos);


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