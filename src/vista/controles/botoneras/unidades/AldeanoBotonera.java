package vista.controles.botoneras.unidades;

import javafx.fxml.FXMLLoader;
import modelo.unidades.Aldeano;
import vista.controles.MapaControl;

import java.net.URL;
import java.util.ResourceBundle;

public class AldeanoBotonera extends UnidadBotonera<Aldeano> {

    public AldeanoBotonera(Aldeano unidad, MapaControl mapa) {
        super(unidad, mapa);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/AldeanoBotonera.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void actualizarUI(){
    }
}

