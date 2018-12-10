package vista.controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import modelo.unidades.Espadachin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EspadachinImagen extends GridPane implements Initializable {

    private final Espadachin espadachin;
    private double vidaInicial;

    @FXML private ProgressBar vidaProgressBar;
    @FXML private Label vidaLabel;
    @FXML private Label nombreLabel;

    public EspadachinImagen(Espadachin espadachin){

        super();
        this.espadachin = espadachin;
        this.vidaInicial = espadachin.getVida();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/EspadachinImagen.fxml"));
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
        this.nombreLabel.setText(this.espadachin.getClass().getSimpleName());
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