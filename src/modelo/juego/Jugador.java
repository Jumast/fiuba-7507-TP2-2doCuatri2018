package modelo.juego;

import javafx.scene.control.Label;
import modelo.Edificio;
import modelo.IPosicionable;
import modelo.edificios.*;
import modelo.excepciones.OroInsuficienteException;
import modelo.posicion.*;
import modelo.unidades.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Jugador {
    private Plebe plebe;
    private Construcciones construcciones;
    private Ejercito ejercito;
    private Castillo castillo;
    private int bolsaDeOro;
    private static final int oroInicial = 100;
    private String nombre;

    private Collection<IPosicionable> posicionables = new ArrayList<>();

    public Jugador(String nombre, Castillo castillo) {
        this.plebe = new Plebe();
        this.construcciones = new Construcciones();
        this.ejercito = new Ejercito();
        this.nombre = nombre;
        this.castillo = castillo;
        this.bolsaDeOro = oroInicial;

        this.posicionables.add(castillo);
    }

    public void pagarCosto(int costo) {
        if (this.bolsaDeOro >= costo) {
            this.bolsaDeOro = this.bolsaDeOro - costo;
        }
        else{
            throw new OroInsuficienteException();
        }
    }

    public String devolverNombre(){
        return this.nombre;
    }

    public void trabajar() {
        int oroRecolectado = this.plebe.trabajar();
        this.bolsaDeOro = (this.bolsaDeOro + oroRecolectado);
    }

    public void agregar(Edificio edificio) {
        this.construcciones.agregarEdificio(edificio);
        this.posicionables.add(edificio);
    }

    public void agregar(Aldeano aldeano){
        this.plebe.agregar(aldeano);
        this.posicionables.add(aldeano);
    }

    public void agregar(UnidadMilitar unidadMilitar){
        this.ejercito.agregarUnidad(unidadMilitar);
        this.posicionables.add(unidadMilitar);
    }

    public Boolean esDuenoDe(IPosicionable posicionable){
        return this.posicionables.contains(posicionable);
    }


    public void recolectorDeCadaveres(){
        this.plebe.borrarCadaveres();
        this.ejercito.borrarCadaveres();
        this.construcciones.limpiarEscombros();
    }

    public boolean castilloDestruido(){
        return !(this.castillo.sigueEnPie());
    }

    public int cantidadDePoblacion(){
        return (this.plebe.cantidadDeAldeanos() + this.ejercito.cantidadDeSoldados());
    }

    public int getOro(){
       return this.bolsaDeOro;
    }
}
