package vista;

import modelo.edificios.Castillo;
import modelo.edificios.Cuartel;
import modelo.edificios.PlazaCentral;
import modelo.unidades.Aldeano;
import modelo.unidades.ArmaDeAsedio;
import modelo.unidades.Arquero;
import modelo.unidades.Espadachin;
import vista.controladores.*;
import vista.controladores.edificios.CastilloController;
import vista.controladores.edificios.CuartelControler;
import vista.controladores.edificios.PlazaCentralController;
import vista.controladores.unidades.*;
import vista.controles.JuegoControl;
import vista.controles.MapaControl;

public class PosicionableControllerFactory {


    private JuegoControl juegoControl;
    private MapaControl mapaControl;
    private String color;
    private String dueño;

    public PosicionableControllerFactory(JuegoControl juegoControl, MapaControl mapaControl, String color, String dueño){

        this.juegoControl = juegoControl;
        this.mapaControl = mapaControl;
        this.color = color;
        this.dueño = dueño;
    }


    public IPosicionableController crearControlador(Aldeano aldeano){
        return new AldeanoController(aldeano, this.color, this.mapaControl, this.juegoControl, this.dueño);

    }

    public IPosicionableController crearControlador(Castillo castillo){
        return new CastilloController(castillo, this.color, mapaControl, this.juegoControl);
    }

    public IPosicionableController crearControlador(PlazaCentral plazaCentral){
        return new PlazaCentralController(plazaCentral, this.color, mapaControl, this.juegoControl);
    }

    public IPosicionableController crearControlador(Espadachin espadachin){
        return new EspadachinController(espadachin, this.color, this.mapaControl, this.juegoControl, this.dueño);
    }

    public IPosicionableController crearControlador(Arquero arquero){
        return new ArqueroController(arquero, this.color, this.mapaControl, this.juegoControl, this.dueño);
    }

    public IPosicionableController crearControlador(ArmaDeAsedio armaDeAsedio){
        return new ArmaDeAsedioController(armaDeAsedio, this.color, this.mapaControl, this.juegoControl, this.dueño);
    }

    public IPosicionableController crearControlador(Cuartel cuartel){
        return new CuartelControler(cuartel, this.color, this.mapaControl, this.juegoControl);
    }

}
