package vista.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;
import modelo.unidades.Aldeano;
import vista.controles.AldeanoBotonera;
import vista.controles.MapaControl;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class AldeanoController implements IPosicionableController, Initializable {

    @FXML private GridPane root;
    @FXML private ImageView imageView;

    AldeanoBotonera botonera;
    private String estado = "seleccionable";


    private Consumer<Aldeano> accion = (aldeano) -> {};
    public void onClicked(Consumer<Aldeano> accion){
        this.accion = accion;
    }

    private IAtacante atacante;
    public void estadoAtaquePotencial(IAtacante atacante){
        this.atacante = atacante;
        this.estado = "ataquePotencial";
    }
    public void estadoSeleccionable(){
        this.estado = "seleccionable";
    }

    private Aldeano aldeano;
    private String color;
    private MapaControl mapaControl;
    private IJuegoController juegoController;

    public AldeanoController(Aldeano aldeano, String color, MapaControl mapaControl, IJuegoController juegoController){
        this.aldeano = aldeano;
        this.color = color;
        this.mapaControl = mapaControl;
        this.juegoController = juegoController;

        AldeanoBotonera botonera = new AldeanoBotonera(aldeano, mapaControl);
        this.botonera = botonera;
    }

    @Override
    public IPosicionable getPosicionable() {
        return this.aldeano;
    }

    @Override
    public Posicion getPosicion() {
        return aldeano.getPosicion();
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public void handleClick(MouseEvent mouseEvent) {

        if(this.estado.equals("seleccionable")){
            this.juegoController.setBotonera(botonera);
        }

        if(this.estado.equals("ataquePotencial")){

           try {
               this.atacante.atacar(this.aldeano);
               new Alert(Alert.AlertType.INFORMATION, "Ataque concretado").show();
               this.botonera.actualizarUI();

               this.accion.accept(this.aldeano);
           }
           catch (Exception e){
               new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
           }

           finally {
               this.mapaControl.estadoSeleccionable();
           }

        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.root.getStylesheets().add(this.getClass().getResource("/vista/css/Aldeano.css").toExternalForm());
        this.imageView.getStyleClass().add(color);
    }



}
