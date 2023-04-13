package org.example;

import lombok.Getter;
import lombok.Setter;

public class Participante {

@Getter @Setter
    private String nombre;
@Getter @Setter
    private int Ronda;
 @Getter @Setter
    private int Puntaje;


    public Participante(String nombre, int Ronda,  int Puntaje) {
        this.nombre = nombre;
        this.Ronda = Ronda;
        this.Puntaje = Puntaje;


    }
}
