package vista.controles.botoneras.unidades;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import modelo.Unidad;
import vista.controles.botoneras.Botonera;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


abstract public class UnidadVista<TUnidad extends Unidad> extends Botonera implements Initializable  {

    @FXML private ProgressBar vidaProgressBar;
    @FXML private Label vidaLabel;
    @FXML private Label nombreLabel;
    protected TUnidad unidad;
    private double vidaInicial;

    protected abstract FXMLLoader getLoader();

    public UnidadVista(TUnidad unidad){

        super();
        this.vidaInicial = unidad.getVida();
        this.unidad = unidad;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/AldeanoImagen.fxml"));
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
        this.nombreLabel.setText(this.unidad.getClass().getSimpleName());
    }

    private double obtenerProgresoDeVida(){
        return this.unidad.getVida() / this.vidaInicial;
    }
    private void setVidaLabel(){
        String vidaInicial = String.valueOf((int)this.vidaInicial);
        String vidaActual = String.valueOf(this.unidad.getVida());
        String texto = String.format("Vida: %s/%s", vidaActual, vidaInicial);

        this.vidaLabel.setText(texto);

    }

    public void actualizarUI(){
        this.vidaProgressBar.setProgress(this.obtenerProgresoDeVida());
        this.setVidaLabel();
    }
}
