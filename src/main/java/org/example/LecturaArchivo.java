package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class LecturaArchivo {
        @Getter @Setter
        private BufferedReader lector;
        @Getter @Setter
        private String linea;
        @Getter @Setter
        private String partes[] = null;
        List<Partido> partidos = new ArrayList<> ();

        public void leerArchivo (String ruta){
            try {
                lector = new BufferedReader ( new FileReader ( ruta ) );
                leerArchivo ("\\C:\\Users\\PC\\Documents\\resultados.csv\\"  );
                while ((linea= lector.readLine ())!= null) {
                   String [] partes = linea.split ( ";" );
                    imprimirLinea();
                    int id = Integer.parseInt ( partes [0] );
                    Equipo equipo1 = new Equipo (partes [1]);
                    int golesEquipo1 = Integer.parseInt ( partes [2] );
                    int golesEquipo2 = Integer.parseInt ( partes [3] );
                    Equipo equipo2 = new Equipo (partes [4]);
                    Partido partido = new Partido ( id,equipo1,equipo2,golesEquipo1,golesEquipo2 );
                    partidos.add ( partido );

                }
                lector.close ();
                linea= null;
                partes = null;
            }catch (Exception e){
                JOptionPane.showMessageDialog ( null, e );
            }
        }
        public void imprimirLinea() {
            for (int i = 0; i < partes.length; i++) ;
            System.out.println ( partes[0] + " |  " );
            Ronda ronda = new Ronda (1, partidos.toArray (), 48, 1);

            List<Pronostico> pronosticos = new ArrayList<> ();

            try {
                File ruta = null;
                lector = new BufferedReader ( new FileReader ( ruta ) );
                leerArchivo ( "\\C:\\Users\\PC\\Documents\\pronostico.csv\\" );
                while ((linea = lector.readLine ()) != null) {
                    String[] partes = linea.split ( ";" );
                    imprimirLinea ();
                    int id =  Integer.parseInt ( partes[0] );
                    Partido partido = buscarPartidoporid (partidos, id);
                    String empate = (partes [3]);
                    String ganados = (partes [4]);
                    ResultadoEnum resultado = ResultadoEnum.ganador;
                    Equipo equipo =partido.getEquipo1 ();
                    if (empate == "x"){ equipo= partido.getEquipo1 (); ResultadoEnum = Resultados.empate;}
                    if (ganados== "x") {equipo = partido.getEquipo2 ();}
                    Pronostico pronostico = new Pronostico ();
                    pronosticos.add ( pronostico );


                }
            } catch (IOException e) {
                throw new RuntimeException ( e );
            }
        }}