package org.example;

import lombok.Getter;
import lombok.Setter;

public class Pronostico {
     @Setter
     @Getter
     private Partido partido;
     @Getter
     @Setter
     private Equipo equipo;
     @Getter
     @Setter
     private Resultado resultados;

     public void Pronostico(Partido partido, Equipo equipo,  Resultado resultados){
          this.partido= partido;
          this.equipo = equipo;
          this.resultados = resultados;
     }

     public int puntos() {
     int result =0;
     if (this.partido.resultados ( this.equipo) == this.resultados){
     result = 1;
}
return result;

}}
