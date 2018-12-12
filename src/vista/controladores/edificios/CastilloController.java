package vista.controladores.edificios;

import modelo.edificios.Castillo;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.Botonera;
import vista.controles.botoneras.edificios.CastilloBotonera;
import vista.controles.MapaControl;

public class CastilloController extends AtacableController<Castillo> {

    private CastilloBotonera botonera;

    public CastilloController(Castillo edificio, String color, MapaControl mapaControl, IJuegoController juegoController) {
        super(edificio, color, mapaControl, juegoController);

        this.botonera = new CastilloBotonera(edificio, mapaControl);
    }

    @Override
    protected String getWavFile() {
        return "edificio_atacado.wav";
    }

    @Override
    protected Botonera getBotonera() {
        return this.botonera;
    }
}

