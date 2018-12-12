package vista.controladores.unidades;

import modelo.unidades.Arquero;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.unidades.ArqueroBotonera;
import vista.controles.botoneras.Botonera;
import vista.controles.MapaControl;


public class ArqueroController extends AtacableController<Arquero> {

    private ArqueroBotonera botonera;

    public ArqueroController(Arquero unidad, String color, MapaControl mapaControl, IJuegoController juegoController) {
        super(unidad, color, mapaControl, juegoController);

        this.botonera = new ArqueroBotonera(unidad, mapaControl);
    }

    @Override
    protected Botonera getBotonera() {
        return this.botonera;
    }

    @Override
    protected String getWavFile(){
        return "unidad_atacada.wav";
    }
}

