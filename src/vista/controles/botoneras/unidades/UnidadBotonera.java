package vista.controles.botoneras.unidades;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import modelo.Unidad;
import vista.controladores.ConstruccionController;
import vista.controladores.MovimientoController;
import vista.controles.MapaControl;
import vista.controles.botoneras.Botonera;

import java.io.IOException;


public abstract class UnidadBotonera<TUnidad extends Unidad> extends Botonera implements Initializable {

    protected TUnidad unidad;

    protected abstract FXMLLoader getLoader();

    public UnidadBotonera(TUnidad unidad, MapaControl mapa){

        super();
        this.unidad = unidad;

        FXMLLoader loader = this.getLoader();
        loader.setRoot(this);
        loader.setController(this);

        loader.setControllerFactory(type -> {

            if(type.equals(MovimientoController.class)){
                return new MovimientoController(unidad, mapa);
            }else if(type.equals(ConstruccionController.class)){
                return new ConstruccionController(mapa);
            }

            else {
                try {
                    return type.newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc);
                }
            }

        });


        try {
            loader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
