package vista.controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import modelo.unidades.Espadachin;
import vista.controladores.MovimientoController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EspadachinBotonera extends Botonera implements Initializable {

    private final Espadachin espadachin;
    private double vidaInicial;
    private MapaControl mapa;

    @FXML private ProgressBar vidaProgressBar;
    @FXML private Label vidaLabel;
    @FXML private Label nombreLabel;

    public EspadachinBotonera(Espadachin espadachin, MapaControl mapa){

        super();
        this.espadachin = espadachin;
        this.vidaInicial = espadachin.getVida();
        this.mapa = mapa;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/EspadachinBotonera.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.setControllerFactory(type -> {

            if(type.equals(MovimientoController.class)){
                return new MovimientoController(espadachin, mapa);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setVidaLabel();
        this.vidaProgressBar.setProgress(this.obtenerProgresoDeVida());
        this.nombreLabel.setText(this.espadachin.getClass().getSimpleName());
    }


    public void handleAtaque(){
        mapa.estadoAtaque(this.espadachin);
        this.playSound();
    }

    private void playSound(){

        try
        {

            String file = "/vista/sonidos/ataque_espadachin.wav";
            URL path = getClass().getResource(file);
            AudioClip ac = new AudioClip(path.toString());
            ac.setVolume(1);
            ac.play();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void handleCancelar(){
        mapa.estadoSeleccionable();
    }

    private double obtenerProgresoDeVida(){
        return this.espadachin.getVida() / this.vidaInicial;
    }
    private void setVidaLabel(){
        String vidaInicial = String.valueOf((int)this.vidaInicial);
        String vidaActual = String.valueOf(this.espadachin.getVida());
        String texto = String.format("Vida: %s/%s", vidaActual, vidaInicial);

        this.vidaLabel.setText(texto);

    }

    public void actualizarUI(){
        this.vidaProgressBar.setProgress(this.obtenerProgresoDeVida());
        this.setVidaLabel();
    }


}
