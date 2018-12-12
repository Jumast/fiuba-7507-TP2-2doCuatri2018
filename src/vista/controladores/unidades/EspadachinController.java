package vista.controladores.unidades;

import modelo.unidades.Espadachin;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.Botonera;
import vista.controles.botoneras.unidades.EspadachinBotonera;
import vista.controles.MapaControl;

public class EspadachinController extends AtacableController<Espadachin> {

    private EspadachinBotonera botonera;

    public EspadachinController(Espadachin unidad, String color, MapaControl mapaControl, IJuegoController juegoController) {
        super(unidad, color, mapaControl, juegoController);

        this.botonera = new EspadachinBotonera(unidad, mapaControl);
    }

    @Override
    protected Botonera getBotonera() {
        return this.botonera;
    }

    @Override
    protected String getWavFile() {
        return "unidad_atacada.wav";
    }
}
