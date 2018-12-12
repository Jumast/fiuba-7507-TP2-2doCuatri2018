package vista.controles.botoneras.unidades;

import javafx.fxml.FXMLLoader;
import modelo.unidades.Arquero;

public class ArqueroImagen extends UnidadVista<Arquero> {

    public ArqueroImagen(Arquero arquero){
        super(arquero);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/ArqueroImagen.fxml"));
    }
}
