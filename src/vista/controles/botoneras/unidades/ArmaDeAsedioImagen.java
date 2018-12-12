package vista.controles.botoneras.unidades;

import javafx.fxml.FXMLLoader;
import modelo.unidades.ArmaDeAsedio;

public class ArmaDeAsedioImagen extends UnidadVista<ArmaDeAsedio> {


    public ArmaDeAsedioImagen(ArmaDeAsedio armaDeAsedio){
        super(armaDeAsedio);
    }

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/ArmaDeAsedioImagen.fxml"));
    }
}
