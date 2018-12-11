package vista.controladores;

import com.sun.scenario.effect.impl.state.RenderState;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
import modelo.unidades.Espadachin;
import vista.controles.EspadachinBotonera;
import vista.controles.EspadachinImagen;
import vista.controles.MapaControl;

import java.net.URL;
import java.util.ResourceBundle;

public class EspadachinController implements IPosicionableController, Initializable {

    @FXML
    private GridPane root;
    @FXML private ImageView imageView;


    private final EspadachinBotonera botonera;
    private Espadachin espadachin;
    private String color;
    private MapaControl mapaControl;
    private IJuegoController juegoController;
    private IAtacante atacante;
    private String dueño;
    private final EspadachinImagen imagen;

    private String estado = "";

    public EspadachinController(Espadachin espadachin, String color, MapaControl mapaControl, IJuegoController juegoController, String dueño){
        this.espadachin = espadachin;
        this.color = color;
        this.mapaControl = mapaControl;
        this.juegoController = juegoController;
        this.dueño = dueño;


        this.botonera = new EspadachinBotonera(espadachin, mapaControl);
        this.imagen = new EspadachinImagen(espadachin);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.root.getStylesheets().add(this.getClass().getResource("/vista/css/Espadachin.css").toExternalForm());
        this.imageView.getStyleClass().add(color);
    }

    @Override
    public IPosicionable getPosicionable() {
        return this.espadachin;
    }

    @Override
    public Posicion getPosicion() {
        return espadachin.getPosicion();
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public void handleClick(MouseEvent mouseEvent) {
        this.juegoController.setImagen(this.imagen);

        if( (this.juegoController.esDelJugador(this.dueño)) ){
            this.juegoController.setBotonera(this.botonera);
            this.estado = "";
        }

        else {
            if (this.estado.equals("ataquePotencial")) {
                this.estado = "";
                try {
                    this.atacante.atacar(this.espadachin);
                    new Alert(Alert.AlertType.INFORMATION, "Ataque concretado").show();
                    this.playSound();
                    this.imagen.actualizarUI();
                }

                catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
                }

                finally {
                    this.mapaControl.estadoSeleccionable();
                }
            }
            this.juegoController.cleanBotonera();
        }
    }

    @Override
    public void estadoAtaquePotencial(IAtacante atacante) {
        this.estado = "ataquePotencial";
        this.atacante = atacante;
    }

    public void estadoSeleccionable(){
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
}
