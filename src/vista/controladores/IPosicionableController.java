package vista.controladores;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;

public interface IPosicionableController {

    IPosicionable getPosicionable();
    Posicion getPosicion();
    String getColor();
    void handleClick(MouseEvent mouseEvent);

    public void estadoAtaquePotencial(IAtacante atacante);
    void estadoSeleccionable();
}
