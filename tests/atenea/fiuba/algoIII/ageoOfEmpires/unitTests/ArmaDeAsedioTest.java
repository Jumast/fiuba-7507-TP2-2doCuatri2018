package atenea.fiuba.algoIII.ageoOfEmpires.unitTests;

import modelo.IAtacable;
import modelo.IEstrategiaAtaque;
import modelo.excepciones.OperacionInvalidaDadoElEstadoActualDelObjetoExcepcion;
import modelo.movimiento.IDireccion;
import modelo.posicion.Posicion;
import modelo.unidades.ArmaDeAsedio;
import modelo.unidades.ArmaDeAsedioNoPuedeAtacarCuandoEstaDesmontadaExcepcion;
import org.junit.Test;
import org.mockito.Mockito;

public class ArmaDeAsedioTest {

    private ArmaDeAsedio crearArmaDeAsedio(){
        return new ArmaDeAsedio(Mockito.mock(Posicion.class), Mockito.mock(IEstrategiaAtaque.class));
    }

    @Test(expected = ArmaDeAsedioNoPuedeAtacarCuandoEstaDesmontadaExcepcion.class)
    public void atacar_CuandoNoEstaMontada_LanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();

        // Act
        armaDeAsedio.atacar(Mockito.mock(IAtacable.class));
    }

    @Test(expected = ArmaDeAsedioNoPuedeAtacarCuandoEstaDesmontadaExcepcion.class)
    public void atacar_LuegoDeMontarYDesmontar_LanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();
        armaDeAsedio.desmontar();

        // Act
        armaDeAsedio.atacar(Mockito.mock(IAtacable.class));
    }

    @Test(expected = OperacionInvalidaDadoElEstadoActualDelObjetoExcepcion.class)
    public void mover_CuandoEstaMontada_LanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();

        // Act
        armaDeAsedio.mover(Mockito.mock(IDireccion.class));
    }

    @Test(expected = OperacionInvalidaDadoElEstadoActualDelObjetoExcepcion.class)
    public void mover_LuegoDeDesmotarYMontar_LanzaExcepcion(){

        // Arrange
        ArmaDeAsedio armaDeAsedio= this.crearArmaDeAsedio();
        armaDeAsedio.montar();
        armaDeAsedio.desmontar();
        armaDeAsedio.montar();

        // Act
        armaDeAsedio.mover(Mockito.mock(IDireccion.class));
    }

}
