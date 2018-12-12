package vista.controles.botoneras.unidades;

import javafx.fxml.FXMLLoader;
import modelo.unidades.Espadachin;

public class EspadachinImagen extends UnidadVista<Espadachin> {

    public EspadachinImagen(Espadachin espadachin){
        super(espadachin);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/EspadachinImagen.fxml"));
    }
}
