package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
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
                while ((linea= lector.readLine ())!= null) {
                    partes = linea.split ( "," );
                    imprimirLinea();
                  


                }
                lector.close ();
                linea= null;
                partes = null;
            }catch (Exception e){
                JOptionPane.showMessageDialog ( null, e );
            }
        }
        public void imprimirLinea(){
            for (int i = 0; i < partes.length; i++);
            System.out.println (partes[0] + " |  ");

        }



    }
