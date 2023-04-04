import lombok.Getter;
import lombok.Setter;
public class Equipo {
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String descripcion;
    public Equipo (String nombre){
        this.nombre =nombre;
    }

}