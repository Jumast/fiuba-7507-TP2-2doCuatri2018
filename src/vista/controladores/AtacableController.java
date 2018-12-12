package vista.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import modelo.IAtacable;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;
import vista.controles.botoneras.Botonera;
import vista.controles.MapaControl;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AtacableController<TAtacable extends IAtacable> implements IPosicionableController, Initializable {

    @FXML
    protected GridPane root;
    @FXML protected ImageView imageView;

    //    Botonera botonera;
    abstract protected Botonera getBotonera();
    private String estado = "seleccionable";

    private IAtacante atacante;
    public void estadoAtaquePotencial(IAtacante atacante){
        this.atacante = atacante;
        this.estado = "ataquePotencial";
    }
    public void estadoSeleccionable(){
        this.estado = "seleccionable";
    }

    private TAtacable unidad;
    protected String color;
    private MapaControl mapaControl;
    private IJuegoController juegoController;

    public AtacableController(TAtacable unidad, String color, MapaControl mapaControl, IJuegoController juegoController){
        this.unidad = unidad;
        this.color = color;
        this.mapaControl = mapaControl;
        this.juegoController = juegoController;

    }

    @Override
    public IPosicionable getPosicionable() {
        return this.unidad;
    }

    @Override
    public Posicion getPosicion() {
        return unidad.getPosicion();
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public void handleClick(MouseEvent mouseEvent) {

        if(this.estado.equals("seleccionable")){
            this.juegoController.setBotonera(this.getBotonera());
        }

        if(this.estado.equals("ataquePotencial")){

            try {
                this.atacante.atacar(this.unidad);
                new Alert(Alert.AlertType.INFORMATION, "Ataque concretado").show();
                this.getBotonera().actualizarUI();
                this.playSound();
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
        String className = this.unidad.getClass().getSimpleName();
        String resource = String.format("/vista/css/%s.css", className);
        this.root.getStylesheets().add(this.getClass().getResource(resource).toExternalForm());
        this.imageView.getStyleClass().add(color);
    }


    protected abstract String getWavFile();

    private void playSound(){

        try
        {

            String f = String.format("/vista/sonidos/%s", this.getWavFile());
            URL path = getClass().getResource(f);
            AudioClip ac = new AudioClip(path.toString());
            ac.play();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
