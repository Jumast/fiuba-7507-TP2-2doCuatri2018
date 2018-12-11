package vista.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;
import modelo.unidades.ArmaDeAsedio;
import vista.controles.ArmaDeAsedioBotonera;
import vista.controles.MapaControl;

import java.net.URL;
import java.util.ResourceBundle;

public class ArmaDeAsedioController implements IPosicionableController, Initializable {


    @FXML
    private GridPane root;
    @FXML private ImageView imageView;


    private final ArmaDeAsedioBotonera botonera;
    private ArmaDeAsedio armaDeAsedio;
    private String color;
    private MapaControl mapaControl;
    private IJuegoController juegoController;
    private IAtacante atacante;

    private String estado = "seleccionable";

    public ArmaDeAsedioController(ArmaDeAsedio armaDeAsedio, String color, MapaControl mapaControl, IJuegoController juegoController){
        this.armaDeAsedio = armaDeAsedio;
        this.color = color;
        this.mapaControl = mapaControl;
        this.juegoController = juegoController;


        this.botonera = new ArmaDeAsedioBotonera(armaDeAsedio, mapaControl, this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.root.getStylesheets().add(this.getClass().getResource("/vista/css/ArmaDeAsedio.css").toExternalForm());
        this.imageView.getStyleClass().add("red-desmontada");
    }

    @Override
    public IPosicionable getPosicionable() {
        return this.armaDeAsedio;
    }

    @Override
    public Posicion getPosicion() {
        return armaDeAsedio.getPosicion();
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
                this.atacante.atacar(this.armaDeAsedio);
                new Alert(Alert.AlertType.INFORMATION, "Ataque concretado").show();
                this.playSound();
                this.botonera.actualizarUI();
            }
            catch (Exception e){
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();

            }

            finally {
                this.mapaControl.estadoSeleccionable();
            }
        }
    }

    private void playSound(){

        try
        {

            String file = "/vista/sonidos/asedio.wav";
            URL path = getClass().getResource(file);
            AudioClip ac = new AudioClip(path.toString());
            ac.play();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void estadoAtaquePotencial(IAtacante atacante) {
        this.estado = "ataquePotencial";
        this.atacante = atacante;
    }

    public void estadoSeleccionable(){
        this.estado = "seleccionable";
    }

    public void montar(){
        this.imageView.getStyleClass().clear();
        this.imageView.getStyleClass().add(String.format("%s-montada", this.color));
    }

    public void desmontar() {
        this.imageView.getStyleClass().clear();
        this.imageView.getStyleClass().add(String.format("%s-desmontada", this.color));
    }


}
