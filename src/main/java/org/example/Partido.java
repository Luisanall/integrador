package org.example;

import lombok.Getter;
import lombok.Setter;

public class Partido {
    @Getter @Setter
    private String Equipo1;
    @Getter@Setter
    private  String Equipo2;
    @Getter@Setter
    private int golesEquipo1;
    @Getter@Setter
    private int golesEquipo2;

    public Partido(String substring, int i, String substring1,int i1) {
    }

    public String toString() {
        return Equipo1 + "  |  " + golesEquipo1 + "  | " + Equipo2 + "  |  "  + golesEquipo2;
    }


}
