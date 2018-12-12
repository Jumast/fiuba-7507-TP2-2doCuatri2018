package vista.controladores.unidades;

import modelo.unidades.Aldeano;
import vista.controladores.AtacableController;
import vista.controladores.IJuegoController;
import vista.controles.MapaControl;
import vista.controles.botoneras.Botonera;
import vista.controles.botoneras.unidades.AldeanoBotonera;
import vista.controles.botoneras.unidades.AldeanoImagen;

import javax.swing.text.html.ImageView;


public class AldeanoController extends AtacableController<Aldeano> {


    private AldeanoBotonera botonera;
    private AldeanoImagen imagen;

    public AldeanoController(Aldeano unidad, String color, MapaControl mapaControl, IJuegoController juegoController, String dueño) {
        super(unidad, color, mapaControl, juegoController, dueño);

        this.botonera = new AldeanoBotonera(unidad, mapaControl);
        this.imagen = new AldeanoImagen(unidad);

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

