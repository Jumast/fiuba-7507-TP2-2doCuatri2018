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
public class ArqueroAtaqueEnRangoTest {

    private Arquero atacante;
    private IAtacable atacado;
    private int danioEsperado;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        final int DANIO_ESPERADO_UNIDADES = 15;
        final int DANIO_ESPERADO_EDIFICIOS = 10;

        final int DISTANCIA_ATAQUE_1 = 1;
        final int DISTANCIA_ATAQUE_2 = 2;
        final int DISTANCIA_ATAQUE_3 = 3;

        IEstrategiaAtaque<Arquero> estrategiaAtaqueArquero = new EstrategiaAtaqueArquero();
        Arquero arquero = new Arquero(Mockito.mock(Posicion.class), estrategiaAtaqueArquero);
        Posicion posicionArquero = arquero.getPosicion();

        Posicion posicionADistancia1 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArquero.distanciaA(posicionADistancia1)).thenReturn(DISTANCIA_ATAQUE_1);

        Posicion posicionADistancia2 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArquero.distanciaA(posicionADistancia2)).thenReturn(DISTANCIA_ATAQUE_2);

        Posicion posicionADistancia3 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArquero.distanciaA(posicionADistancia3)).thenReturn(DISTANCIA_ATAQUE_3);

        ArrayList collection = new ArrayList();

        // unidades a distancia 1
        collection.add(new Object[]{arquero, new Aldeano(posicionADistancia1), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new Espadachin(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new Arquero(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new ArmaDeAsedio(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // unidades a distancia 2
        collection.add(new Object[]{arquero, new Aldeano(posicionADistancia2), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new Espadachin(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new Arquero(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new ArmaDeAsedio(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // unidades a distancia 3
        collection.add(new Object[]{arquero, new Aldeano(posicionADistancia3),DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new Espadachin(posicionADistancia3, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new Arquero(posicionADistancia3, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{arquero, new ArmaDeAsedio(posicionADistancia3, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // Construcciones a distancia 1
        collection.add(new Object[]{arquero, new PlazaCentral(posicionADistancia1, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{arquero, new Cuartel(posicionADistancia1, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{arquero, new Castillo(posicionADistancia1, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        // Construcciones a distancia 2
        collection.add(new Object[]{arquero, new PlazaCentral(posicionADistancia2, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{arquero, new Cuartel(posicionADistancia2, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{arquero, new Castillo(posicionADistancia2, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        // Construcciones a distancia 3
        collection.add(new Object[]{arquero, new PlazaCentral(posicionADistancia3, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{arquero, new Cuartel(posicionADistancia3, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{arquero, new Castillo(posicionADistancia3, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        return collection;

    }

    // Constructor
    public ArqueroAtaqueEnRangoTest(Arquero atacante, IAtacable atacado, int danioEsperado){
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
