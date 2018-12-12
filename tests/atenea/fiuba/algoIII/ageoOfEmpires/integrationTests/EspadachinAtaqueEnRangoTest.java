package atenea.fiuba.algoIII.ageoOfEmpires.integrationTests;

import modelo.IAtacable;
import modelo.IEstrategiaAtaque;
import modelo.edificios.*;
import modelo.posicion.Posicion;
import modelo.unidades.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class EspadachinAtaqueEnRangoTest {

    private Espadachin atacante;
    private IAtacable atacado;
    private int danioEsperado;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        final int DANIO_ESPERADO_UNIDADES = 25;
        final int DANIO_ESPERADO_EDIFICIOS = 15;
        final int DISTANCIA_ATAQUE_1 = 1;

        IEstrategiaAtaque<Espadachin> estrategiaAtaqueEspadachin = new EstrategiaAtaqueEspadachin();
        Espadachin espadachin = new Espadachin(Mockito.mock(Posicion.class), estrategiaAtaqueEspadachin);
        Posicion posicionEspadachin = espadachin.getPosicion();

        Posicion posicionADistancia1 = Mockito.mock(Posicion.class);
        Mockito.when(posicionEspadachin.distanciaA(posicionADistancia1)).thenReturn(DISTANCIA_ATAQUE_1);

        ArrayList collection = new ArrayList();

        // unidades a distancia 1
        collection.add(new Object[]{espadachin, new Aldeano(posicionADistancia1), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{espadachin, new Espadachin(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{espadachin, new Arquero(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{espadachin, new ArmaDeAsedio(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // Construcciones a distancia 1
        collection.add(new Object[]{espadachin, new PlazaCentral(posicionADistancia1, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{espadachin, new Cuartel(posicionADistancia1, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{espadachin, new Castillo(posicionADistancia1, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        return collection;

    }

    // Constructor
    public EspadachinAtaqueEnRangoTest(Espadachin atacante, IAtacable atacado, int danioEsperado){
        this.atacante = atacante;
        this.atacado = atacado;
        this.danioEsperado = danioEsperado;
    }

    @Test
    public void atacar_DentroDelRangoDeAtaque_ProduceDanioEsperado(){

        // Arrange
        int vidaInicialAtacado = atacado.getVida();

        // Act
        atacante.atacar(atacado);
        int vidaFinalAtacado = atacado.getVida();
        int danioProducido = vidaInicialAtacado - vidaFinalAtacado;

        // Assert
        Assert.assertEquals(danioEsperado, danioProducido);

    }

}
