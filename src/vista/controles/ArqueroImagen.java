package vista.controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import modelo.unidades.Arquero;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArqueroImagen  extends GridPane implements Initializable {

    private final Arquero arquero;
    private double vidaInicial;

    @FXML private ProgressBar vidaProgressBar;
    @FXML private Label vidaLabel;
    @FXML private Label nombreLabel;

    public ArqueroImagen(Arquero arquero){

        super();
        this.arquero = arquero;
        this.vidaInicial = arquero.getVida();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/ArqueroImagen.fxml"));
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
        this.nombreLabel.setText(this.arquero.getClass().getSimpleName());
    }


    private double obtenerProgresoDeVida(){
        return this.arquero.getVida() / this.vidaInicial;
    }

    private void setVidaLabel(){
        String vidaInicial = String.valueOf((int)this.vidaInicial);
        String vidaActual = String.valueOf(this.arquero.getVida());
        String texto = String.format("Vida: %s/%s", vidaActual, vidaInicial);

        this.vidaLabel.setText(texto);

    }

    public void actualizarUI(){
        this.vidaProgressBar.setProgress(this.obtenerProgresoDeVida());
        this.setVidaLabel();
    }

}
