package vista.controles.botoneras.unidades;

import javafx.fxml.FXMLLoader;
import modelo.unidades.Espadachin;
import vista.controles.MapaControl;
import vista.utilidades.ReproductorDeSonido;

public class EspadachinBotonera extends UnidadBotonera<Espadachin> {

    private MapaControl mapa;

    @Override
    protected FXMLLoader getLoader() {
        return new FXMLLoader(getClass().getResource("/vista/vistas/ArqueroBotonera.fxml"));
    }

    public EspadachinBotonera(Espadachin espadachin, MapaControl mapa) {
        super(espadachin, mapa);
        this.mapa = mapa;
    }


    public void handleAtaque(){
        mapa.estadoAtaque(this.unidad);
        new ReproductorDeSonido("ataque_espadachin.wav").reproducirSonido();
    }

    public void handleCancelar(){
        mapa.estadoSeleccionable();
    }


}
