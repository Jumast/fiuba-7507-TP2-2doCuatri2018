package vista.controles;

public class DescripcionJugador {

    private String nombreJugador;
    private String color;

    public DescripcionJugador(String nombreJugador, String color){
        this.color = nombreJugador;
        this.nombreJugador = color;
    }

    public String devolverColor(){
        return this.color;
    }

    public String devolverJugador(){
        return this.nombreJugador;
    }
}
