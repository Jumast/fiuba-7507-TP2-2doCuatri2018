package vista.controladores.unidades;

import modelo.unidades.Aldeano;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.unidades.AldeanoBotonera;
import vista.controles.botoneras.Botonera;
import vista.controles.MapaControl;


public class AldeanoController extends AtacableController<Aldeano> {


    private AldeanoBotonera botonera;

    public AldeanoController(Aldeano unidad, String color, MapaControl mapaControl, IJuegoController juegoController) {
        super(unidad, color, mapaControl, juegoController);

        this.botonera = new AldeanoBotonera(unidad, mapaControl);
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

