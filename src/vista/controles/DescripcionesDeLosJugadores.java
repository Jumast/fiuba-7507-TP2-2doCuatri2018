package vista.controles;

import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DescripcionesDeLosJugadores {

    private List<DescripcionJugador> descripcionJugadorLista;

    public DescripcionesDeLosJugadores(){
        this.descripcionJugadorLista = new LinkedList<>();
    }

    public void agregarDescripcion(DescripcionJugador descripcionJugador){
        this.descripcionJugadorLista.add(descripcionJugador);
    }

    public String devolverColorDelJugador(String nombre){
        boolean encontrado = false;
        int i= 0;
        String descripcion = "";

        while ( !encontrado  &&  i < this.descripcionJugadorLista.size()){

            descripcion = descripcionJugadorLista.get(i).devolverJugador();

            if (descripcion == nombre){
                encontrado = true;
                descripcion = this.descripcionJugadorLista.get(i).devolverColor();
            }

            i++;
        }
        return descripcion;
    }
}
