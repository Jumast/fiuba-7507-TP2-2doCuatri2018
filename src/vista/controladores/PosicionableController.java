package vista.controladores;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;

public class PosicionableController implements IPosicionableController {

    private IPosicionable posicionable;
    private String color;

    private ScrollPane scrollPane;

    public PosicionableController(IPosicionable posicionable, String color){

        this.posicionable = posicionable;
        this.color = color;
    }


    @Override
    public IPosicionable getPosicionable() {
        return posicionable;
    }

    @Override
    public Posicion getPosicion() {
        return posicionable.getPosicion();
    }

    @Override
    public String getColor() {
        return color;
    }


    @Override
    public void handleClick(MouseEvent mouseEvent) {
        ScrollPane pane = this.scrollPane;

        double width = pane.getContent().getBoundsInLocal().getWidth();
        double height = pane.getContent().getBoundsInLocal().getHeight();

        double x = posicionable.getPosicion().getAbajoIzquierda().getCoordenadaEnX();
        double y = posicionable.getPosicion().getAbajoIzquierda().getCoordenadaEnY();

        // scrolling values range from 0 to 1
        pane.setVvalue(y/height);
        pane.setHvalue(x/width);

        // just for usability
//        node.requestFocus();
    }

    @Override
    public void estadoAtaquePotencial(IAtacante atacante) {

    }

    @Override
    public void estadoSeleccionable() {

    }
}
