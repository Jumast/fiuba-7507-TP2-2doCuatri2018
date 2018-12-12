package vista.controladores.unidades;

import modelo.unidades.ArmaDeAsedio;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.unidades.ArmaDeAsedioBotonera;
import vista.controles.botoneras.Botonera;
import vista.controles.MapaControl;
import vista.utilidades.ReproductorDeSonido;

import java.net.URL;
import java.util.ResourceBundle;

public class ArmaDeAsedioController extends AtacableController<ArmaDeAsedio> {


    ArmaDeAsedioBotonera botonera;

    @Override
    protected Botonera getBotonera() {
        return this.botonera;
    }

    public ArmaDeAsedioController(ArmaDeAsedio armaDeAsedio, String color, MapaControl mapaControl, IJuegoController juegoController){
        super(armaDeAsedio, color, mapaControl, juegoController);

        this.botonera = new ArmaDeAsedioBotonera(armaDeAsedio, mapaControl, this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.root.getStylesheets().add(this.getClass().getResource("/vista/css/ArmaDeAsedio.css").toExternalForm());
        this.imageView.getStyleClass().add("red-desmontada");
    }

    @Override
    protected String getWavFile(){
        return "arma_asedio_atacada.wav";
    }


    public void montar(){
        this.imageView.getStyleClass().clear();
        this.imageView.getStyleClass().add(String.format("%s-montada", this.color));

        new ReproductorDeSonido("montar_asedio.wav").reproducirSonido();
    }

    public void desmontar() {
        this.imageView.getStyleClass().clear();
        this.imageView.getStyleClass().add(String.format("%s-desmontada", this.color));
        new ReproductorDeSonido("montar_asedio.wav").reproducirSonido();
    }


}
