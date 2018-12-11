package vista.controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import modelo.IPosicionable;
import vista.controladores.ArmaDeAsedioController;
import vista.controladores.IPosicionController;
import vista.controladores.IPosicionableController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArmaDeAsedioVista extends GridPane {

    public ArmaDeAsedioVista(IPosicionableController controller){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/PosicionableVista.fxml"));
        loader.setRoot(this);
        loader.setController(controller);

        try {
            loader.load();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

    }

}
