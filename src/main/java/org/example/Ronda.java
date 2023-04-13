package org.example;

import lombok.Getter;
import lombok.Setter;

public class Ronda {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private Partido [] partidos;
    @Getter @Setter
    private String numero;
    @Getter @Setter
     private int puntos;
     public  Ronda (int id, Object[] variosPartidos, int Unnumero, int puntaje){
         this.id = id;
         this.numero = String.valueOf ( Unnumero );
         this.puntos = getPuntos ();

     }
}
