package vista.controles.botoneras.edificios;

import javafx.fxml.FXMLLoader;
import modelo.edificios.Castillo;
import vista.controles.MapaControl;

public class CastilloBotonera extends EdificioBotonera<Castillo> {

    public CastilloBotonera(Castillo unidad, MapaControl mapa) {
        super(unidad, mapa);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/CastilloBotonera.fxml"));
    }

}
