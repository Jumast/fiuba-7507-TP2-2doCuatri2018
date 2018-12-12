package vista.controles.botoneras.edificios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import modelo.Edificio;
import vista.controles.MapaControl;
import vista.controles.botoneras.Botonera;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class EdificioBotonera<TEdificio extends Edificio> extends Botonera implements Initializable {


        @FXML
    private ProgressBar vidaProgressBar;
    @FXML private Label vidaLabel;
    @FXML private Label nombreLabel;
    TEdificio edificio;
    private double vidaInicial;

    protected abstract FXMLLoader getLoader();

    public EdificioBotonera(TEdificio edificio, MapaControl mapa){

        super();
        this.edificio = edificio;
        this.vidaInicial = edificio.getVida();

        FXMLLoader loader = this.getLoader();
        loader.setRoot(this);
        loader.setController(this);

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
        this.nombreLabel.setText(this.edificio.getClass().getSimpleName());
    }

    private double obtenerProgresoDeVida(){
        return this.edificio.getVida() / this.vidaInicial;
    }
    private void setVidaLabel(){
        String vidaInicial = String.valueOf((int)this.vidaInicial);
        String vidaActual = String.valueOf(this.edificio.getVida());
        String texto = String.format("Vida: %s/%s", vidaActual, vidaInicial);

        this.vidaLabel.setText(texto);

    }

    public void actualizarUI(){
        this.vidaProgressBar.setProgress(this.obtenerProgresoDeVida());
        this.setVidaLabel();
    }

}
