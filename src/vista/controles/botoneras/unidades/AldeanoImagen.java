package vista.controles.botoneras.unidades;

import javafx.fxml.FXMLLoader;
import modelo.unidades.Aldeano;

public class AldeanoImagen extends UnidadVista<Aldeano> {

    public AldeanoImagen(Aldeano aldeano) {
        super(aldeano);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/AldeanoImagen.fxml"));
    }
}

