package vista.controles.botoneras.edificios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import modelo.edificios.Castillo;
import vista.controladores.edificios.CreacionUnidadesCastilloController;
import vista.controladores.edificios.CreacionUnidadesCuartelController;
import vista.controles.MapaControl;
import vista.controles.botoneras.VidaController;

public class CastilloBotonera extends EdificioBotonera<Castillo> {


    @FXML GridPane contruccionBotonera;

    public CastilloBotonera(Castillo unidad, MapaControl mapa) {
        super(unidad, mapa);
    }

    @Override
    protected FXMLLoader getLoader() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/edificios/CastilloBotonera.fxml"));
        loader.setController(this);

        loader.setControllerFactory(type -> {
            if(type == CreacionUnidadesCastilloController.class){
                CreacionUnidadesCastilloController controller = new CreacionUnidadesCastilloController(this.edificio, this.mapa);
                controller.onCreacionUnidad(this::deshabilitar);
                return controller;
            }
            if (type.equals(VidaController.class)) {
                VidaController vidaController = new VidaController(this.edificio);
                this.vidaController= vidaController;
                return vidaController;
            }
            else {
                return null;
            }
        });
        return loader;
    }

    @Override
    public void habilitar() {
        this.contruccionBotonera.setDisable(false);
    }

    @Override
    public void deshabilitar() {
        this.contruccionBotonera.setDisable(true);
    }
}
