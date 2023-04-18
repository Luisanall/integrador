package org.example;

import lombok.Getter;
import lombok.Setter;

public class Ronda {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private Object[] partidos;
    @Getter @Setter
    private int numero;
    @Getter @Setter
    private int puntos;
    public  Ronda ( int id, Object[] partidos,  int numero, int puntos) {
        this.id = id;
        this.partidos = partidos;
        this.numero = numero;
        this.puntos = puntos;
    }

}