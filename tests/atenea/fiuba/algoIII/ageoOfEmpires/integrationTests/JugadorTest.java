package atenea.fiuba.algoIII.ageoOfEmpires.integrationTests;

import modelo.edificios.Castillo;
import modelo.edificios.EdificiosFabrica;
import modelo.edificios.PlazaCentral;
import modelo.excepciones.EdificioNoEsPropioException;
import modelo.excepciones.OroInsuficienteException;
import modelo.excepciones.UnidadNoEsPropiaException;
import modelo.juego.Jugador;
import modelo.unidades.Aldeano;
import modelo.unidades.ArmaDeAsedio;
import modelo.unidades.UnidadesFabrica;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.AbstractList;

public class JugadorTest {


    @Test (expected = OroInsuficienteException.class)
    public void crearUnJugadorCon100DeOroGastarOroYQueNoSeaSuficienteTest() {
        Jugador jugador = new Jugador("Pablo", null, null);
        jugador.pagarCosto(120);
    }

    @Test
    public void jugadorCreadoConCastilloEnPieTest() {
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugador = new Jugador("Pablo", castillo, null);
        Assert.assertFalse(jugador.castilloDestruido());
    }

    @Test (expected = UnidadNoEsPropiaException.class)
    public void AJugadorCreadoPreguntaSiAldeanoLePerteneceLanzaExcepcionTest() {
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugador = new Jugador("Pablo", castillo, null);
        jugador.esMio(new UnidadesFabrica().crearAldeano());
    }

    @Test (expected = UnidadNoEsPropiaException.class)
    public void AJugadorCreadoPreguntaSiAqueroLePerteneceLanzaExcepcionTest() {
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugador = new Jugador("Pablo", castillo, null);
        jugador.esMio(new UnidadesFabrica().crearArquero());
    }

    @Test (expected = EdificioNoEsPropioException.class)
    public void AJugadorCreadoPreguntaSiCastilloLePerteneceLanzaExcepcionTest() {
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugador = new Jugador("Pablo", castillo, null);
        jugador.esMio(new EdificiosFabrica().crearCastillo());
    }

    @Test (expected = OroInsuficienteException.class)
    public void jugadorCreadoAgregaAldeanoYNoRecolectaOroTest() {
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugador = new Jugador("Pablo", castillo, null);
        jugador.agregar(new UnidadesFabrica().crearAldeano());
        jugador.pagarCosto(180);
    }

    @Test
    public void jugadorCreadoPierdeCastilloTest() {
        Castillo castillo = Mockito.mock(Castillo.class);
        Mockito.when(castillo.sigueEnPie()).thenReturn(false);
        Jugador jugador = new Jugador("Pablo", castillo, null);
        Assert.assertTrue(jugador.castilloDestruido());
    }

    @Test
    public void jugadorCreadoAgregaUnidadMilitarTest() {
        ArmaDeAsedio armaDeAsedio = new UnidadesFabrica().crearArmaDeAsedio();
        Jugador jugador = new Jugador("Pablo", null, null);
        jugador.agregar(armaDeAsedio);
        jugador.esMio(armaDeAsedio);
    }

    @Test (expected = UnidadNoEsPropiaException.class)
    public void jugadorCreadoSeFijaSiTieneUnaUnidadMilitarSinHaberlaAgregadoTest() {
        ArmaDeAsedio armaDeAsedio = new UnidadesFabrica().crearArmaDeAsedio();
        Jugador jugador = new Jugador("Pablo", null, null);
        jugador.esMio(armaDeAsedio);
    }

    @Test
    public void jugadorCreadoCon3AldeanosY100DeOroHaceTrabajarALosAldeanosYPaga160Test() {
        Jugador jugador = new Jugador("Pablo", null, null);
        jugador.agregar(new UnidadesFabrica().crearAldeano());
        jugador.agregar(new UnidadesFabrica().crearAldeano());
        jugador.agregar(new UnidadesFabrica().crearAldeano());
        jugador.trabajar();
        jugador.pagarCosto(160);
    }

    @Test (expected = OroInsuficienteException.class)
    public void jugadorCreadoCon3AldeanosY100DeOroHaceTrabajarALosAldeanosYPaga190Test() {
        Jugador jugador = new Jugador("Pablo", null, null);
        jugador.agregar(new UnidadesFabrica().crearAldeano());
        jugador.agregar(new UnidadesFabrica().crearAldeano());
        jugador.agregar(new UnidadesFabrica().crearAldeano());
        jugador.trabajar();
        jugador.pagarCosto(190);
    }

    @Test (expected = UnidadNoEsPropiaException.class)
    public void jugadorCreadoRecolectaCadaveresTest() {
        Jugador jugador = new Jugador("Pablo", null, null);
        Aldeano aldeano = Mockito.mock(Aldeano.class);
        Mockito.when(aldeano.estaMuerto()).thenReturn(true);
        jugador.agregar(aldeano);
        jugador.recolectorDeCadaveres();
        jugador.esMio(aldeano);
    }

    @Test
    public void jugadorCreadoDevuelveSuColorTest() {
        Jugador jugador = new Jugador("Pablo", null, "Red");
        Assert.assertEquals("Red", jugador.obtenerColor());
    }
}
