package modelo.juego;

import modelo.ListaCircular;
import modelo.excepciones.UltimoHombreEnPieException;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Turno {

    private ListaCircular<Jugador> listaDeParticipantes;
    private Jugador jugadorActual;
    private int cantidadDeParticipantes;

    public Turno(List<Jugador> listaDeParticipantes){
        this.generarListaDeOrdenAleatorio(listaDeParticipantes);
        this.jugadorActual = this.listaDeParticipantes.getFirst();
    }

    public Jugador devolverJugadorActual(){
        return jugadorActual;
    }

    public void cambiarDeTurno(){
        this.removerJugadoresDerrotados();
        if(cantidadDeParticipantes > 1){
            this.jugadorActual = this.listaDeParticipantes.getSiguiente();
        }
        else {
            throw new UltimoHombreEnPieException();
        }
    }


    private void generarListaDeOrdenAleatorio(List<Jugador> listaOriginal) {
        this.cantidadDeParticipantes = listaOriginal.size();
        ListaCircular listaAleatoria = new ListaCircular<>();
        while (listaAleatoria.size() < listaOriginal.size()) {
            int randomPosicion = new Random().nextInt(cantidadDeParticipantes);
            Jugador jugador = listaOriginal.get(randomPosicion);
            if (!listaAleatoria.contains(jugador)) {
                listaAleatoria.addLast(jugador);
            }
        }
       this.listaDeParticipantes = listaAleatoria;
    }

    private void removerJugadoresDerrotados(){
        for(int i = 0; i < this.listaDeParticipantes.size(); i++){
            if(this.listaDeParticipantes.get(i).castilloDestruido()){
                this.listaDeParticipantes.remove(i);
                this.cantidadDeParticipantes--;
            }
        }
    }
}
