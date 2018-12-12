package vista.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;

import java.net.URL;
import java.util.ResourceBundle;

public class EdificioEnConstruccionController implements IPosicionableController, Initializable {


    @FXML
    protected GridPane root;
    @FXML protected ImageView imageView;

    private IPosicionable posicionable;
    private String tipo;
    private String color;

    private ScrollPane scrollPane;

    public EdificioEnConstruccionController(IPosicionable posicionable, String tipo,String color){

        this.posicionable = posicionable;
        this.tipo = tipo;
        this.color = color;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String className = this.unidad.getClass().getSimpleName();
        String resource = String.format("/vista/css/%s.css", tipo);
        this.root.getStylesheets().add(this.getClass().getResource(resource).toExternalForm());
        this.imageView.getStyleClass().add(color);
    }


    @Override
    public IPosicionable getPosicionable() {
        return posicionable;
    }

    @Override
    public Posicion getPosicion() {
        return posicionable.getPosicion();
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void handleClick(MouseEvent mouseEvent) {
        new Alert(Alert.AlertType.INFORMATION, "Edificio en construcción").show();

    }

    @Override
    public void estadoAtaquePotencial(IAtacante atacante) {

    }

    @Override
    public void estadoSeleccionable() {

    }
}
