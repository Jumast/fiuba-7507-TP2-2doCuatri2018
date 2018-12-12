package vista.controladores.unidades;

import modelo.unidades.Arquero;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.botoneras.unidades.ArqueroBotonera;
import vista.controles.botoneras.Botonera;
import vista.controles.botoneras.unidades.ArqueroImagen;
import vista.controles.MapaControl;


public class ArqueroController extends AtacableController<Arquero> {

    private ArqueroBotonera botonera;
    private ArqueroImagen imagen;


    public ArqueroController(Arquero unidad, String color, MapaControl mapaControl, IJuegoController juegoController, String dueño) {
        super(unidad, color, mapaControl, juegoController, dueño);

        this.botonera = new ArqueroBotonera(unidad, mapaControl);
        this.imagen = new ArqueroImagen(unidad);
    }

    @Override
    protected Botonera getBotonera() {
        return this.botonera;
    }

    @Override
    protected Botonera getImagen(){return this.imagen;}

    @Override
    protected String getWavFile(){
        return "unidad_atacada.wav";
    }
}

