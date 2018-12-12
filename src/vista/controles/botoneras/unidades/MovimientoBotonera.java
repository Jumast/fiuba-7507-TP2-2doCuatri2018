package vista.controles.botoneras.unidades;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MovimientoBotonera extends BorderPane {

    public MovimientoBotonera(){


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/MovimientoBotonera.fxml"));
        loader.setRoot(this);
        loader.setController(this);


        try {
            loader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleIzquierdaArriba(){
        new Alert(Alert.AlertType.CONFIRMATION, "Izquierda Arriba");
    }

}
