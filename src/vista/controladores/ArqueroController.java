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
import modelo.unidades.Arquero;
import vista.controles.ArqueroBotonera;
import vista.controles.MapaControl;

import java.net.URL;
import java.util.ResourceBundle;

public class ArqueroController implements IPosicionableController, Initializable {


    @FXML
    private GridPane root;
    @FXML private ImageView imageView;

    private final ArqueroBotonera botonera;
    private Arquero arquero;
    private String color;
    private MapaControl mapaControl;
    private IJuegoController juegoController;
    private IAtacante atacante;

    private String estado = "seleccionable";

    public ArqueroController(Arquero arquero, String color, MapaControl mapaControl, IJuegoController juegoController){
        this.arquero = arquero;
        this.color = color;
        this.mapaControl = mapaControl;
        this.juegoController = juegoController;


        this.botonera = new ArqueroBotonera(arquero, mapaControl);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.root.getStylesheets().add(this.getClass().getResource("/vista/css/Arquero.css").toExternalForm());
        this.imageView.getStyleClass().add(color);
    }

    @Override
    public IPosicionable getPosicionable() {
        return this.arquero;
    }

    @Override
    public Posicion getPosicion() {
        return arquero.getPosicion();
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
                this.atacante.atacar(this.arquero);
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

            String file = "/vista/sonidos/recibir_ataque_asedio.wav";
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

}
