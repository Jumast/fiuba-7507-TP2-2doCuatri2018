package vista.controladores.unidades;

import modelo.unidades.Espadachin;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.Botonera;
import vista.controles.botoneras.unidades.AldeanoImagen;
import vista.controles.botoneras.unidades.EspadachinBotonera;
import vista.controles.MapaControl;
import vista.controles.botoneras.unidades.EspadachinImagen;

public class EspadachinController extends AtacableController<Espadachin> {

    private EspadachinBotonera botonera;
    private EspadachinImagen imagen;

    public EspadachinController(Espadachin unidad, String color, MapaControl mapaControl, IJuegoController juegoController, String dueño) {
        super(unidad, color, mapaControl, juegoController, dueño);

        this.botonera = new EspadachinBotonera(unidad, mapaControl);
        this.imagen = new EspadachinImagen(unidad);
    }

    @Override
    protected Botonera getBotonera() {
        return this.botonera;
    }

    @Override
    protected Botonera getImagen(){return this.imagen;}

    @Override
    protected String getWavFile() {
        return "unidad_atacada.wav";
    }
}
