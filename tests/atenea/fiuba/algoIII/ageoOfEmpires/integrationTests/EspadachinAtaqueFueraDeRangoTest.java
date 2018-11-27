package atenea.fiuba.algoIII.ageoOfEmpires.integrationTests;

import atenea.fiuba.algoIII.ageoOfEmpires.modelo.IAtacable;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.IEstrategiaAtaque;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.edificios.*;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.excepciones.UnidadFueraDeRangoDeAtaqueExcepcion;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.posicion.Posicion;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.unidades.*;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.unidades.Aldeano;
import atenea.fiuba.algoIII.ageoOfEmpires.modelo.unidades.ArmaDeAsedio;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class EspadachinAtaqueFueraDeRangoTest {

    private Espadachin atacante;
    private IAtacable unidad;
    private int danioEsperado;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        final int DANIO_ESPERADO = 0;
        final int DISTANCIA_ATAQUE_2 = 2;

        IEstrategiaAtaque<Espadachin> estrategiaAtaqueEspadachin = new EstrategiaAtaqueEspadachin();
        Espadachin espadachin = new Espadachin(Mockito.mock(Posicion.class), estrategiaAtaqueEspadachin);
        Posicion posicionAtacante = espadachin.getPosicion();

        Posicion posicionADistancia2 = Mockito.mock(Posicion.class);
        Mockito.when(posicionAtacante.distanciaA(posicionADistancia2)).thenReturn(DISTANCIA_ATAQUE_2);

        ArrayList collection = new ArrayList();

        // Unidades a distancia 2
        collection.add(new Object[]{espadachin, new Aldeano(posicionADistancia2, Mockito.mock(EdificiosEnConstruccionFabrica.class)), DANIO_ESPERADO});
        collection.add(new Object[]{espadachin, new Espadachin(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});
        collection.add(new Object[]{espadachin, new Arquero(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});
        collection.add(new Object[]{espadachin, new ArmaDeAsedio(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});

        // Construcciones a distancia 2
        collection.add(new Object[]{espadachin, new PlazaCentral(posicionADistancia2, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO});
        collection.add(new Object[]{espadachin, new Cuartel(posicionADistancia2, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO});
        collection.add(new Object[]{espadachin, new Castillo(posicionADistancia2, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO});

        return collection;

    }


    public EspadachinAtaqueFueraDeRangoTest(Espadachin atacante, IAtacable atacable, int danioEsperado){
        this.atacante = atacante;
        this.unidad = atacable;
        this.danioEsperado = danioEsperado;
    }

    @Test(expected = UnidadFueraDeRangoDeAtaqueExcepcion.class)
    public void atacar_FueraDeRangoDeAtaque_LanzaExcepcion(){

        // Act
        atacante.atacar(unidad);

    }

    @Test(expected = UnidadFueraDeRangoDeAtaqueExcepcion.class)
    public void atacar_FueraDeRangoDeAtaque_NoProduceDanio(){

        // Arrange
        int vidaInicialAtacado = unidad.getVida();

        // Act
        atacante.atacar(unidad);
        int vidaFinalAtacado = unidad.getVida();
        int danioProducido = vidaInicialAtacado - vidaFinalAtacado;

        // Assert
        Assert.assertEquals(danioEsperado, danioProducido);
    }

}
