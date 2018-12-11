package vista.controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import modelo.unidades.Arquero;
import vista.controladores.MovimientoController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArqueroBotonera extends GridPane implements Initializable {

    private final Arquero arquero;
    private MapaControl mapa;

    public ArqueroBotonera(Arquero arquero, MapaControl mapa){

        super();
        this.arquero = arquero;
        this.mapa = mapa;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/ArqueroBotonera.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.setControllerFactory(type -> {

            if(type.equals(MovimientoController.class)){
                return new MovimientoController(arquero, mapa);
            }

            else {
                try {
                    return type.newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc);
                }
            }

        });


        try {
            loader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void handleAtaque(){
        mapa.estadoAtaque(this.arquero);
        this.playSound();
    }

    private void playSound(){

        try
        {

            String file = "/vista/sonidos/ataque_arquero.wav";
            URL path = getClass().getResource(file);
            AudioClip ac = new AudioClip(path.toString());
            ac.play();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void handleCancelar(){
        mapa.estadoSeleccionable();
    };

}
