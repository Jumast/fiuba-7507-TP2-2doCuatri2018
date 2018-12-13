package atenea.fiuba.algoIII.ageoOfEmpires.unitTests;

import modelo.edificios.Castillo;
import modelo.edificios.EdificiosFabrica;
import modelo.juego.Turno;
import modelo.juego.Jugador;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TurnoTest {

    @Test
    public void permaneceEnElTurnoDeUnJugadorHastaQuePase(){
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugadorJuan =new Jugador("Juan", castillo);
        Jugador jugadorPablo =new Jugador("Pablo", castillo);

        List<Jugador> listaDeParticipantes = new ArrayList<>();
        listaDeParticipantes.add(jugadorJuan);
        listaDeParticipantes.add((jugadorPablo));

        Turno turno = new Turno(listaDeParticipantes);

        String jugadorActual1 = turno.devolverJugadorActual().devolverNombre();
        String jugadorActual2 = turno.devolverJugadorActual().devolverNombre();
        Assert.assertEquals(jugadorActual1, jugadorActual2);
    }

    @Test
    public void alPasarDeTurnoCambiaDeJugador(){
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugadorJuan = new Jugador("Juan", castillo);
        Jugador jugadorPablo = new Jugador("Pablo", castillo);

        List<Jugador> listaDeParticipantes = new ArrayList<>();
        listaDeParticipantes.add(jugadorJuan);
        listaDeParticipantes.add((jugadorPablo));

        Turno turno = new Turno(listaDeParticipantes);
        String jugadorActual1 = turno.devolverJugadorActual().devolverNombre();
        turno.cambiarDeTurno();
        String jugadorActual2 = turno.devolverJugadorActual().devolverNombre();
        Assert.assertNotEquals(jugadorActual1, jugadorActual2);

    }

    @Test
    public void seCreanDosJugadoresYEsElTurnoDelUltimoAlPasarSigueElPrimero(){
        Castillo castillo = new EdificiosFabrica().crearCastillo();
        Jugador jugadorJuan =new Jugador("Juan", castillo);
        Jugador jugadorPablo =new Jugador("Pablo", castillo);

        List<Jugador> listaDeParticipantes = new ArrayList<>();
        listaDeParticipantes.add(jugadorJuan);
        listaDeParticipantes.add((jugadorPablo));

        Turno turno = new Turno(listaDeParticipantes);
        String jugadorActual1 = turno.devolverJugadorActual().devolverNombre();
        turno.cambiarDeTurno();
        turno.cambiarDeTurno();
        String jugadorActual2 = turno.devolverJugadorActual().devolverNombre();
        Assert.assertEquals(jugadorActual1, jugadorActual2);
    }
}
