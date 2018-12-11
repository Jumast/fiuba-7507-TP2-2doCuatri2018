package vista.controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import modelo.unidades.ArmaDeAsedio;
import vista.controladores.ArmaDeAsedioController;

import java.net.URL;
import java.util.ResourceBundle;

public class ArmaDeAsedioImagen extends GridPane implements Initializable {

    private final ArmaDeAsedio armaDeAsedio;
    private double vidaInicial;
    private ArmaDeAsedioController controller;

    @FXML private ProgressBar vidaProgressBar;
    @FXML private Label vidaLabel;
    @FXML private Label nombreLabel;

    private Boolean montada = false;

    public ArmaDeAsedioImagen(ArmaDeAsedio armaDeAsedio){
        super();
        this.armaDeAsedio = armaDeAsedio;
        this.vidaInicial = armaDeAsedio.getVida();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/ArmaDeAsedioImagen.fxml"));
        loader.setRoot(this);
        loader.setController(this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setVidaLabel();
        this.vidaProgressBar.setProgress(this.obtenerProgresoDeVida());
        this.nombreLabel.setText(this.armaDeAsedio.getClass().getSimpleName());
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
