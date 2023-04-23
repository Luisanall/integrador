package org.example;

import lombok.Getter;
import lombok.Setter;


public class Partido {
    @Setter @Getter
    private int Ronda;
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String Equipo1;
    @Getter
    @Setter
    private String Equipo2;
    @Getter
    @Setter
    private int CantidadGoles1;
    @Getter
    @Setter
    private int CantidadGoles2;


    public Partido(int Ronda , int id, String Equipo1, int CantidadGoles1, int CantidadGoles2, String Equipo2) {
        this.Ronda = Ronda;
        this.id = id;
        this.Equipo1 = Equipo1;
        this.CantidadGoles1 = CantidadGoles1;
        this.CantidadGoles2= CantidadGoles2;
        this.Equipo2 = Equipo2;
    }


    public Resultado resultados(Equipo unEquipo) {
        if (this.CantidadGoles1 == this.getCantidadGoles2 ()) {
            return Resultado.empate;
        }
        if (this.Equipo1 == unEquipo.getNombre ()) {
            if (this.CantidadGoles1 > this.CantidadGoles2) {
                return Resultado.ganador;
            } else return Resultado.perdedor;
        }
        if (this.Equipo2 == unEquipo.getNombre ()) {
            if (this.CantidadGoles2 > this.CantidadGoles1) {
                return Resultado.ganador;
            } else return Resultado.perdedor;

        }
        return Resultado.desconocido;
    }}








