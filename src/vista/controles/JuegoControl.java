package vista.controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.edificios.Castillo;
import modelo.edificios.EstrategiaAtaqueCastillo;
import modelo.edificios.PlazaCentral;
import modelo.juego.Juego;
import modelo.juego.Jugador;
import modelo.juego.Turno;
import modelo.posicion.*;
import modelo.unidades.Aldeano;
import modelo.unidades.Espadachin;
import modelo.unidades.EstrategiaAtaqueEspadachin;
import modelo.unidades.UnidadesFabrica;
import vista.PosicionableControllerFactory;
import javafx.scene.control.Button;
import vista.controladores.*;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JuegoControl extends BorderPane implements Initializable, IJuegoController {


    private Stage primaryStage;
    private Juego juego;
    private List<Jugador> listaDeParticipantes;
    private MapaControl mapaControl;
    private Turno turno;

    @FXML private GridPane botonera;
    @FXML private GridPane imagen;
    @FXML private Button pasarTurno;
    @FXML private Text fichaTecnica;

    JuegoControl(Stage primaryStage, String nombreJugador1, String nombreJugador2) {
        this.listaDeParticipantes = new ArrayList();

        this.primaryStage = primaryStage;

        Juego juego = new Juego(nombreJugador1, nombreJugador2);
        this.juego = juego;
        MiniMapaController miniMapaController = new MiniMapaController(this, juego.getMapa());
        MapaControl mapaControl = new MapaControl(this, juego.getMapa(), juego.getJugador1(), juego.getJugador2(), miniMapaController);
        this.mapaControl =  mapaControl;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas/Juego.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.setControllerFactory(type -> {

            if(type.equals(MiniMapaController.class)){
                return miniMapaController;
            }

            else {
                // default behavior for controllerFactory:
                try {
                    return type.newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc); // fatal, just bail...
                }
            }
        });


        try {
            loader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }


        this.inicializarJugador1(nombreJugador1);
        this.inicializarJugador2(nombreJugador2);

        this.turno = new Turno(this.listaDeParticipantes);
        this.fichaTecnica.setText(this.turno.devolverJugadorActual());

//      mapaControl.dibujar();
        this.centerProperty().setValue(mapaControl);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setBotonera(Node node){
        this.botonera.getChildren().clear();
        this.botonera.add(node, 1, 0);
    }

    @Override
    public void cleanBotonera(){
        this.botonera.getChildren().clear();
    }

    @Override
    public void setImagen(Node node){
        this.imagen.getChildren().clear();
        this.imagen.add(node, 1, 0);
    }

    private void inicializarJugador1(String nombreJugador){
        Mapa mapa = this.juego.getMapa();
        Posicion posicionCastillo = new PosicionCuadrado(Limite.SuperiorIzquierdo, new Casillero(0,0), 3);
        Posicion posicionPlazaCentral = new PosicionCuadrado(Limite.SuperiorIzquierdo, new Casillero(3,3), 2);
        Posicion posicionAldeano1 = new PosicionDeUnCasillero(mapa,5,1);
        Posicion posicionAldeano2 = new PosicionDeUnCasillero(mapa,1,5);
        Posicion posicionAldeano3 = new PosicionDeUnCasillero(mapa,5,5);

        Castillo castillo = new Castillo(posicionCastillo,new UnidadesFabrica(), new EstrategiaAtaqueCastillo());
        PlazaCentral plazaCentral = new PlazaCentral(posicionPlazaCentral, new UnidadesFabrica());
        Aldeano aldeano1 = new Aldeano(posicionAldeano1);
        Aldeano aldeano2 = new Aldeano(posicionAldeano2);
        Aldeano aldeano3 = new Aldeano(posicionAldeano3);

        mapa.posicionar(castillo);
        mapa.posicionar(plazaCentral);
        mapa.posicionar(aldeano1);
        mapa.posicionar(aldeano2);
        mapa.posicionar(aldeano3);

        Jugador jugador = new Jugador(nombreJugador, castillo);
        jugador.agregar(plazaCentral);
        jugador.agregar(aldeano1);
        jugador.agregar(aldeano2);
        jugador.agregar(aldeano3);

        this.listaDeParticipantes.add(jugador);

        PosicionableControllerFactory controllerFactory = new PosicionableControllerFactory(this, this.mapaControl, "red", nombreJugador);
        IPosicionableController castilloController = controllerFactory.crearControlador(castillo);
        IPosicionableController plazaCentralController = controllerFactory.crearControlador(plazaCentral);
        IPosicionableController aldeano1Controller = controllerFactory.crearControlador(aldeano1);
        IPosicionableController aldeano2Controller = controllerFactory.crearControlador(aldeano2);
        IPosicionableController aldeano3Controller = controllerFactory.crearControlador(aldeano3);


        this.mapaControl.agregar(castilloController);
        this.mapaControl.agregar(plazaCentralController);
        this.mapaControl.agregar(aldeano1Controller);
        this.mapaControl.agregar(aldeano2Controller);
        this.mapaControl.agregar(aldeano3Controller);

        // Espadachín
        Posicion posicionEspadachin = new PosicionDeUnCasillero(mapa, 7,7);
        Espadachin espadachin = new Espadachin(posicionEspadachin, new EstrategiaAtaqueEspadachin());
        mapa.posicionar(espadachin);

        IPosicionableController espadachinController = controllerFactory.crearControlador(espadachin);
        this.mapaControl.agregar(espadachinController);
    }

    private void inicializarJugador2(String nombreJugador2){
        Mapa mapa = this.juego.getMapa();
        int ancho = mapa.getAncho();
        int alto = mapa.getAlto();

        Posicion posCastillo = new PosicionCuadrado(Limite.SuperiorIzquierdo, new Casillero(mapa.getAncho() - 3, mapa.getAlto() - 3), 3);
        Posicion posPlazaCentral = new PosicionCuadrado(Limite.SuperiorIzquierdo, new Casillero(mapa.getAncho() - 5, mapa.getAlto() - 5), 2);
        Posicion posAldeano1 =  new PosicionDeUnCasillero(mapa, ancho - 6, alto - 2);
        Posicion posAldeano2 = new PosicionDeUnCasillero(mapa, ancho -2, alto - 6);
        Posicion posAldeano3 = new PosicionDeUnCasillero(mapa, ancho - 6, alto -6);

        Castillo castillo = new Castillo(posCastillo,new UnidadesFabrica(), new EstrategiaAtaqueCastillo());
        PlazaCentral plazaCentral = new PlazaCentral(posPlazaCentral, new UnidadesFabrica());
        Aldeano aldeano1 = new Aldeano(posAldeano1);
        Aldeano aldeano2 = new Aldeano(posAldeano2);
        Aldeano aldeano3 = new Aldeano(posAldeano3);

        mapa.posicionar(plazaCentral);
        mapa.posicionar(castillo);
        mapa.posicionar(aldeano1);
        mapa.posicionar(aldeano2);
        mapa.posicionar(aldeano3);

        Jugador jugador = new Jugador(nombreJugador2, castillo);
        jugador.agregar(plazaCentral);
        jugador.agregar(aldeano1);
        jugador.agregar(aldeano2);
        jugador.agregar(aldeano3);

        this.listaDeParticipantes.add(jugador);

        PosicionableControllerFactory controllerFactory = new PosicionableControllerFactory(this, this.mapaControl, "blue", nombreJugador2);
        IPosicionableController castilloController = controllerFactory.crearControlador(castillo);
        IPosicionableController plazaCentralController = controllerFactory.crearControlador(plazaCentral);
        IPosicionableController aldeano1Controller = controllerFactory.crearControlador(aldeano1);
        IPosicionableController aldeano2Controller = controllerFactory.crearControlador(aldeano2);
        IPosicionableController aldeano3Controller = controllerFactory.crearControlador(aldeano3);

        this.mapaControl.agregar(castilloController);
        this.mapaControl.agregar(plazaCentralController);
        this.mapaControl.agregar(aldeano1Controller);
        this.mapaControl.agregar(aldeano2Controller);
        this.mapaControl.agregar(aldeano3Controller);

        // Espadachín
        Posicion posicionEspadachin = new PosicionDeUnCasillero(mapa, 10,7);
        Espadachin espadachin = new Espadachin(posicionEspadachin, new EstrategiaAtaqueEspadachin());
        mapa.posicionar(espadachin);

        IPosicionableController espadachinController = controllerFactory.crearControlador(espadachin);
        this.mapaControl.agregar(espadachinController);
    }


    public void cambioDeTurno(){
        this.turno.cambiarDeTurno();
        this.fichaTecnica.setText(this.turno.devolverJugadorActual());
        this.imagen.getChildren().clear();
        this.botonera.getChildren().clear();
    }

    @Override
    public boolean esDelJugador(String dueño){
        return (this.turno.devolverJugadorActual() == dueño);
    }


}
