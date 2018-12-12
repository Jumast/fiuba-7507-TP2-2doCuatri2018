package vista.controladores.edificios;

import modelo.edificios.PlazaCentral;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.Botonera;
import vista.controles.MapaControl;
import vista.controles.botoneras.edificios.PlazaCentralBotonera;


public class PlazaCentralController extends AtacableController<PlazaCentral> {


    private PlazaCentralBotonera botonera;

    public PlazaCentralController(PlazaCentral edificio, String color, MapaControl mapaControl, IJuegoController juegoController) {
        super(edificio, color, mapaControl, juegoController);

        this.botonera = new PlazaCentralBotonera(edificio, mapaControl);
    }

    @Override
    protected Botonera getBotonera() {
        return this.botonera;
    }

    @Override
    protected String getWavFile() {
        return "edificio_atacado.wav";
    }
}
