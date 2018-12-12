package vista.controles.botoneras.edificios;

import javafx.fxml.FXMLLoader;
import modelo.edificios.Cuartel;
import vista.controles.MapaControl;

public class CuartelBotonera extends EdificioBotonera<Cuartel> {

    public CuartelBotonera(Cuartel edificio, MapaControl mapa) {
        super(edificio, mapa);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/CuartelBotonera.fxml"));
    }
}
