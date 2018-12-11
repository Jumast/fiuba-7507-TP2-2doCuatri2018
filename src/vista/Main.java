package vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import vista.controles.InicioControl;

import java.net.URL;


public class Main extends Application {

    private AudioClip audioClip;

    public Main(){

        try
        {

            String file = "/vista/sonidos/intro.wav";
            URL path = getClass().getResource(file);
            AudioClip ac = new AudioClip(path.toString());
            ac.setVolume(0.03);
            this.audioClip = ac;

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        InicioControl inicioControl = new InicioControl(primaryStage);
        inicioControl.onAceptar(() -> this.audioClip.stop());

        primaryStage.setTitle("Age ofEmpires II");
        primaryStage.setScene(new Scene(inicioControl));
        primaryStage.show();
        this.playSound();
    }

    private void playSound(){

        try
        {

//            String file = "/vista/sonidos/intro.wav";
//            URL path = getClass().getResource(file);
//            AudioClip ac = new AudioClip(path.toString());

            this.audioClip.play(0.3);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
