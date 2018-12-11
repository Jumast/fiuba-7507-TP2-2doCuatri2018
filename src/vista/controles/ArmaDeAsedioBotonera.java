package vista.controles;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import modelo.unidades.ArmaDeAsedio;
import modelo.unidades.Espadachin;
import vista.controladores.ArmaDeAsedioController;
import vista.controladores.MovimientoController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArmaDeAsedioBotonera extends GridPane implements Initializable {

    private final ArmaDeAsedio armaDeAsedio;
    private double vidaInicial;
    private MapaControl mapa;
    private ArmaDeAsedioController controller;

    @FXML
    private ProgressBar vidaProgressBar;
    @FXML private Label vidaLabel;
    @FXML private Label nombreLabel;

    @FXML private Button buttonAtacar;
    @FXML private Button buttonMontar;
    @FXML private Button buttonDesmontar;

    private Boolean montada = false;

    public ArmaDeAsedioBotonera(ArmaDeAsedio armaDeAsedio, MapaControl mapa, ArmaDeAsedioController controller){

        super();
        this.armaDeAsedio = armaDeAsedio;
        this.vidaInicial = armaDeAsedio.getVida();
        this.mapa = mapa;
        this.controller = controller;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/ArmaDeAsedioBotonera.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.setControllerFactory(type -> {

            if(type.equals(MovimientoController.class)){
                return new MovimientoController(armaDeAsedio, mapa);
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
        this.nombreLabel.setText(this.armaDeAsedio.getClass().getSimpleName());

        this.buttonAtacar.setDisable(true);
        this.buttonMontar.setDisable(false);
        this.buttonDesmontar.setDisable(true);
    }


    public void handleAtaque(){
        mapa.estadoAtaque(this.armaDeAsedio);
        this.playSound();

    }

    private void playSound(){

        try
        {

            String file = "/vista/sonidos/ataque_arma_asedio.wav";
            URL path = getClass().getResource(file);
            AudioClip ac = new AudioClip(path.toString());
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

    public void handleMontar(){
        this.armaDeAsedio.montar();
        this.montada = true;


        this.buttonMontar.setDisable(true);
        this.buttonDesmontar.setDisable(false);
        this.buttonAtacar.setDisable(false);

        this.controller.montar();
    }

    public void handleDesmontar(){
        this.armaDeAsedio.desmontar();
        this.montada = false;

        this.buttonMontar.setDisable(false);
        this.buttonDesmontar.setDisable(true);
        this.buttonAtacar.setDisable(true);

        controller.desmontar();
    }


    private double obtenerProgresoDeVida(){
        return this.armaDeAsedio.getVida() / this.vidaInicial;
    }
    private void setVidaLabel(){
        String vidaInicial = String.valueOf((int)this.vidaInicial);
        String vidaActual = String.valueOf(this.armaDeAsedio.getVida());
        String texto = String.format("Vida: %s/%s", vidaActual, vidaInicial);

        this.vidaLabel.setText(texto);

    }

    public void actualizarUI(){
        this.vidaProgressBar.setProgress(this.obtenerProgresoDeVida());
        this.setVidaLabel();
    }


}
