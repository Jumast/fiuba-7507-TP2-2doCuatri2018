package vista.controles;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import vista.controladores.IPosicionableController;

import java.io.IOException;

public class ArqueroVista extends GridPane {

    public ArqueroVista(IPosicionableController controller) {

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
