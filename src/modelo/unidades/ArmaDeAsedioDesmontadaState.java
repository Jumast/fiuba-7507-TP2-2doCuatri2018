package modelo.unidades;

import modelo.IAtacable;
import modelo.excepciones.OperacionInvalidaDadoElEstadoActualDelObjetoExcepcion;
import modelo.movimiento.IDireccion;

import java.util.function.Consumer;

class ArmaDeAsedioDesmontadaState implements IArmaDeAsedioState {

    private Consumer<IDireccion> moverFunc;

    ArmaDeAsedioDesmontadaState(Consumer<IDireccion> moverFunc){
        this.moverFunc = moverFunc;
    }

    @Override
    public void atacar(IAtacable atacable) {
        throw new ArmaDeAsedioNoPuedeAtacarCuandoEstaDesmontadaExcepcion();
    }

    @Override
    public void mover(IDireccion direccion) {
        this.moverFunc.accept(direccion);
    }

}
