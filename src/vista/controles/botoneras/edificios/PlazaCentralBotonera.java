package vista.controles.botoneras.edificios;

import javafx.fxml.FXMLLoader;
import modelo.edificios.PlazaCentral;
import vista.controles.MapaControl;
import vista.controles.botoneras.edificios.EdificioBotonera;

public class PlazaCentralBotonera extends EdificioBotonera<PlazaCentral> {


    public PlazaCentralBotonera(PlazaCentral edificio, MapaControl mapa) {
        super(edificio, mapa);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/PlazaCentralBotonera.fxml"));
    }
}
