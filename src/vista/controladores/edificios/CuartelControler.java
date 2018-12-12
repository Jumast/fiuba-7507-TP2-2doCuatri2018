package vista.controladores.edificios;

import modelo.edificios.Cuartel;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.Botonera;
import vista.controles.botoneras.edificios.CuartelBotonera;
import vista.controles.MapaControl;

public class CuartelControler extends AtacableController<Cuartel> {

    private Botonera botonera = null;

    public CuartelControler(Cuartel edificio, String color, MapaControl mapaControl, IJuegoController juegoController) {
        super(edificio, color, mapaControl, juegoController);

        this.botonera = new CuartelBotonera(edificio, mapaControl);
    }

    @Override
    protected Botonera getBotonera()
    {
        return this.botonera;
    }

    @Override
    protected String getWavFile() {
        return "edificio_atacado.wav";
    }
}
