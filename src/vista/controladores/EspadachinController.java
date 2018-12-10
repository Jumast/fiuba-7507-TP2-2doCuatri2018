package vista.controladores;

import com.sun.scenario.effect.impl.state.RenderState;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;
import modelo.unidades.Espadachin;
import vista.controles.EspadachinBotonera;
import vista.controles.EspadachinImagen;
import vista.controles.MapaControl;

public class EspadachinController implements IPosicionableController {

    private final EspadachinBotonera botonera;
    private Espadachin espadachin;
    private String color;
    private MapaControl mapaControl;
    private IJuegoController juegoController;
    private IAtacante atacante;
    private String dueño;
    private final EspadachinImagen imagen;

    private String estado = "";

    public EspadachinController(Espadachin espadachin, String color, MapaControl mapaControl, IJuegoController juegoController, String dueño){
        this.espadachin = espadachin;
        this.color = color;
        this.mapaControl = mapaControl;
        this.juegoController = juegoController;
        this.dueño = dueño;


        this.botonera = new EspadachinBotonera(espadachin, mapaControl);
        this.imagen = new EspadachinImagen(espadachin);
    }

    @Override
    public IPosicionable getPosicionable() {
        return this.espadachin;
    }

    @Override
    public Posicion getPosicion() {
        return espadachin.getPosicion();
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public void handleClick(MouseEvent mouseEvent) {
        this.juegoController.setImagen(this.imagen);

        if( (this.juegoController.esDelJugador(this.dueño)) ){
            this.juegoController.setBotonera(this.botonera);
            this.estado = "";
        }

        else {
            if (this.estado.equals("ataquePotencial")) {
                this.estado = "";
                try {
                    this.atacante.atacar(this.espadachin);
                    new Alert(Alert.AlertType.INFORMATION, "Ataque concretado").show();
                    this.imagen.actualizarUI();
                }

                catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
                }

                finally {
                    this.mapaControl.estadoSeleccionable();
                }
            }
            this.juegoController.cleanBotonera();
        }
    }

    @Override
    public void estadoAtaquePotencial(IAtacante atacante) {
        this.estado = "ataquePotencial";
        this.atacante = atacante;
    }

    public void estadoSeleccionable(){
    }

}
