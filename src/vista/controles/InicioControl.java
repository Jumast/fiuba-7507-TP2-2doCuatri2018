package vista.controles;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class InicioControl extends GridPane {

    private Stage primaryStage;

    @FXML private TextField nombreJugador1 = new TextField();
    @FXML private TextField nombreJugador2 = new TextField();


    private Runnable onAceptarAction = () -> {};

    public void onAceptar(Runnable onAceptarAction){
        this.onAceptarAction = onAceptarAction;
    }


    public InicioControl(Stage primaryStage){

        super();
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/Inicio.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void handleAceptar(MouseEvent mouseEvent) throws Exception {

        if(this.nombreJugador1.getText().isEmpty() || this.nombreJugador2.getText().isEmpty()){
           Alert alerta = new Alert(Alert.AlertType.ERROR, "Se requieren más jugadores");
           alerta.setTitle("¡Atención!");
           alerta.setHeaderText("Error al iniciar juego");
           alerta.showAndWait();

        }
        else{
            String nombreJugador1 = this.nombreJugador1.getText();
            String nombreJugador2 = this.nombreJugador2.getText();
            JuegoControl juegoControl = new JuegoControl(this.primaryStage, nombreJugador1, nombreJugador2);

            this.primaryStage.setScene(new Scene(juegoControl));
            this.primaryStage.setFullScreen(true);

            this.playSound();
            this.onAceptarAction.run();
        }



    }

    private void playSound(){

        try
        {

            String file = "/vista/sonidos/juegoo.wav";
            URL path = getClass().getResource(file);
            AudioClip ac = new AudioClip(path.toString());
            ac.setVolume(0.5);
            ac.setCycleCount(AudioClip.INDEFINITE);
            ac.play();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleCancelar(MouseEvent mouseEvent) {
        Platform.exit();
    }



}
