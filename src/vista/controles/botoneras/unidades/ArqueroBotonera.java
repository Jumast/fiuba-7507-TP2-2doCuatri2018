package vista.controles.botoneras.unidades;

import javafx.fxml.FXMLLoader;
import modelo.unidades.Arquero;
import vista.controles.MapaControl;
import vista.utilidades.ReproductorDeSonido;


public class ArqueroBotonera extends UnidadBotonera<Arquero> {

    private MapaControl mapa;

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/ArqueroBotonera.fxml"));
    }

    public ArqueroBotonera(Arquero arquero, MapaControl mapa) {
        super(arquero, mapa);
        this.mapa = mapa;
    }


    public void handleAtaque(){
        mapa.estadoAtaque(this.unidad);
        new ReproductorDeSonido("ataque_arquero.wav").reproducirSonido();
    }

    public void handleCancelar(){
        mapa.estadoSeleccionable();
    }


}
