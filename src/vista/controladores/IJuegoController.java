package vista.controladores;

import javafx.scene.Node;

public interface IJuegoController {
    void setBotonera(Node node);

    void setImagen(Node node);

    void cleanBotonera();

    boolean esDelJugador(String dueño);
}
