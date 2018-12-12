package atenea.fiuba.algoIII.ageoOfEmpires.integrationTests;

import modelo.IAtacable;
import modelo.IEstrategiaAtaque;
import modelo.edificios.*;
import modelo.excepciones.UnidadFueraDeRangoDeAtaqueExcepcion;
import modelo.posicion.Posicion;
import modelo.unidades.Aldeano;
import modelo.unidades.ArmaDeAsedio;
import modelo.unidades.Arquero;
import modelo.unidades.Espadachin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArmaDeAsedioAtaqueFueraDeRangoTest {

    private ArmaDeAsedio armaDeAsedio;
    private IAtacable unidad;
    private int danioEsperado;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        final int DANIO_ESPERADO = 0;
        final int DISTANCIA_ATAQUE_6 = 6;

        IEstrategiaAtaque<ArmaDeAsedio> estrategiaAtaque = new EstrategiaAtaqueArmaDeAsedio();
        ArmaDeAsedio armaDeAsedio = new ArmaDeAsedio(Mockito.mock(Posicion.class), estrategiaAtaque);

        Posicion posicionAtacante = armaDeAsedio.getPosicion();

        Posicion posicionADistancia6 = Mockito.mock(Posicion.class);
        Mockito.when(posicionAtacante.distanciaA(posicionADistancia6)).thenReturn(DISTANCIA_ATAQUE_6);

        ArrayList collection = new ArrayList();

        // unidades a distancia 6
        collection.add(new Object[]{armaDeAsedio, new Aldeano(posicionADistancia6), DANIO_ESPERADO});
        collection.add(new Object[]{armaDeAsedio, new Espadachin(posicionADistancia6, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});
        collection.add(new Object[]{armaDeAsedio, new Arquero(posicionADistancia6, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});
        collection.add(new Object[]{armaDeAsedio, new ArmaDeAsedio(posicionADistancia6, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});

        // Construcciones a distancia 6
        collection.add(new Object[]{armaDeAsedio, new PlazaCentral(posicionADistancia6, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO});
        collection.add(new Object[]{armaDeAsedio, new Cuartel(posicionADistancia6, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO});
        collection.add(new Object[]{armaDeAsedio, new Castillo(posicionADistancia6, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});

        return collection;

    }


    public ArmaDeAsedioAtaqueFueraDeRangoTest(ArmaDeAsedio atacante, IAtacable atacable, int danioEsperado){
        this.armaDeAsedio = atacante;
        this.unidad = atacable;
        this.danioEsperado = danioEsperado;
    }

    @Test(expected = UnidadFueraDeRangoDeAtaqueExcepcion.class)
    public void atacar_FueraDeRangoDeAtaque_LanzaExcepcion(){

        // Arrange
        armaDeAsedio.montar();

        // Act
        armaDeAsedio.atacar(unidad);

    }

    @Test(expected = UnidadFueraDeRangoDeAtaqueExcepcion.class)
    public void atacar_FueraDeRangoDeAtaque_NoProduceDanio(){

        // Arrange
        armaDeAsedio.montar();
        int vidaInicialAtacado = unidad.getVida();

        // Act
        armaDeAsedio.atacar(unidad);
        int vidaFinalAtacado = unidad.getVida();
        int danioProducido = vidaInicialAtacado - vidaFinalAtacado;

        // Assert
        Assert.assertEquals(danioEsperado, danioProducido);
    }
}
