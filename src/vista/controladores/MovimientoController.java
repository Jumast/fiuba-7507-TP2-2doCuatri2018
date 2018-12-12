package vista.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.IMovible;
import modelo.movimiento.*;
import vista.controles.MapaControl;
import vista.utilidades.IReproductorDeSonido;
import vista.utilidades.NullReproductorDeSonido;

import java.net.URL;
import java.util.ResourceBundle;

public class MovimientoController implements Initializable {

    @FXML private Label posicionLabel;

    private MapaControl mapa;
    private IMovible unidad;
    private IPosicionController posicionController;

    private String sonido;
    private IReproductorDeSonido reproductorDeSonido = new NullReproductorDeSonido();

    public MovimientoController(IMovible unidad, MapaControl mapa){
        this.mapa = mapa;
        this.unidad = unidad;
        this.posicionController = posicionController;
    }

    public MovimientoController(IMovible unidad, MapaControl mapa, IReproductorDeSonido reproductorDeSonido){
        this(unidad, mapa);
        this.reproductorDeSonido = reproductorDeSonido;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.actualizarPosicion();
    }

    private void actualizarPosicion(){
        int x = this.unidad.getPosicion().getAbajoIzquierda().getCoordenadaEnX();
        int y = this.unidad.getPosicion().getAbajoIzquierda().getCoordenadaEnY();
        this.posicionLabel.setText(String.format("Posicion: %s, %s", x, y));
    }

    @FXML
    private void handleIzquierdaArriba() {
        this.unidad.mover(Direccion.izquierdaArriba());
        this.actualizarUI();
    }

    @FXML
    private void handleArriba() {
        this.unidad.mover(Direccion.arriba());
        this.actualizarUI();
    }

    @FXML
    private void handleDerechaArriba() {
        this.unidad.mover(Direccion.derechaArriba());
        this.actualizarUI();
    }

    @FXML
    private void handleIzquierda() {
        this.unidad.mover(Direccion.izquierda());
        this.actualizarUI();
    }

    @FXML
    private void handleDerecha() {
        this.unidad.mover(Direccion.derecha());
        this.actualizarUI();
    }

    @FXML
    private void handleIzquierdaAbajo() {
        this.unidad.mover(Direccion.izquierdaAbajo());
        this.actualizarUI();
    }

    @FXML
    private void handleAbajo() {
        this.unidad.mover(Direccion.abajo());
        this.actualizarUI();
    }

    @FXML
    private void handleDerechaAbajo() {
        this.unidad.mover(Direccion.derechaAbajo());
        this.actualizarUI();
    }

    private void actualizarUI(){
        this.reproductorDeSonido.reproducirSonido();
        this.mapa.dibujar();
        this.actualizarPosicion();

    }


}
