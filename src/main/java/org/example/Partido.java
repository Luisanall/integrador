package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Partido {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private Equipo equipo1;
    @Getter
    @Setter
    private Equipo equipo2;
    @Getter
    @Setter
    private int golesEquipo1;
    @Getter
    @Setter
    private int golesEquipo2;


    public Partido(int id, Equipo equipo1, int golesEquipo1, int golesEquipo2,Equipo equipo2) {
        this.id = id;
        this.equipo1 = equipo1;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.equipo2 = equipo2;
    }


    public Resultado resultados(Equipo unEquipo) {
        if (this.golesEquipo1 == this.getGolesEquipo2 ()) {
            return Resultado.empate;
        }
        if (this.equipo1.getNombre () == unEquipo.getNombre ()) {
            if (this.golesEquipo1 > this.golesEquipo2) {
                return Resultado.ganador;
            } else return Resultado.perdedor;
        }
        if (this.equipo2.getNombre () == unEquipo.getNombre ()) {
            if (this.golesEquipo1 < this.golesEquipo2) {
                return Resultado.ganador;
            } else return Resultado.perdedor;

        }
        return Resultado.desconocido;
    }}








