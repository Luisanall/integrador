package org.example;

import lombok.Getter;
import lombok.Setter;

public class Pronostico {
     @Setter
     @Getter
     private String Partido;
     @Getter
     @Setter
     private String Equipo;
     @Getter
     @Setter
     private ResultadoEnum resultados;

     public void Pronostico(String Partido, String Equipo, ResultadoEnum resultados) {
          this.Partido = Partido;
          this.Equipo = Equipo;
          this.resultados = resultados;
     }

     public int puntos(int i) {

     }

}


}
