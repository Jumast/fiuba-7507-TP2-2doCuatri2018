package modelo.unidades;

public class ArmaDeAsedioNoPuedeAtacarCuandoEstaDesmontadaExcepcion extends RuntimeException {


    @Override
    public String getMessage(){
        return "Arma de asedio no puede atacar cuando está desmontada";
    }
}
