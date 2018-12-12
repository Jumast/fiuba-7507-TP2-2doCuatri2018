package atenea.fiuba.algoIII.ageoOfEmpires.integrationTests;

import modelo.IAtacable;
import modelo.IEstrategiaAtaque;
import modelo.edificios.*;
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
public class ArmaDeAsedioAtaqueEnRangoTest {

    private ArmaDeAsedio armaDeAsedio;
    private IAtacable atacado;
    private int danioEsperado;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        final int DANIO_ESPERADO_UNIDADES = 0;
        final int DANIO_ESPERADO_EDIFICIOS = 75;

        final int DISTANCIA_ATAQUE_1 = 1;
        final int DISTANCIA_ATAQUE_2 = 2;
        final int DISTANCIA_ATAQUE_3 = 3;
        final int DISTANCIA_ATAQUE_4 = 4;
        final int DISTANCIA_ATAQUE_5 = 5;

        IEstrategiaAtaque<ArmaDeAsedio> estrategiaAtaque = new EstrategiaAtaqueArmaDeAsedio();
        ArmaDeAsedio armaDeAsedio = new ArmaDeAsedio(Mockito.mock(Posicion.class), estrategiaAtaque);
        Posicion posicionArmaDeAsedio = armaDeAsedio.getPosicion();

        Posicion posicionADistancia1 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArmaDeAsedio.distanciaA(posicionADistancia1)).thenReturn(DISTANCIA_ATAQUE_1);

        Posicion posicionADistancia2 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArmaDeAsedio.distanciaA(posicionADistancia2)).thenReturn(DISTANCIA_ATAQUE_2);

        Posicion posicionADistancia3 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArmaDeAsedio.distanciaA(posicionADistancia3)).thenReturn(DISTANCIA_ATAQUE_3);

        Posicion posicionADistancia4 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArmaDeAsedio.distanciaA(posicionADistancia4)).thenReturn(DISTANCIA_ATAQUE_4);

        Posicion posicionADistancia5 = Mockito.mock(Posicion.class);
        Mockito.when(posicionArmaDeAsedio.distanciaA(posicionADistancia5)).thenReturn(DISTANCIA_ATAQUE_5);

        ArrayList collection = new ArrayList();

        // unidades a distancia 1
        collection.add(new Object[]{armaDeAsedio, new Aldeano(posicionADistancia1), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Espadachin(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Arquero(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new ArmaDeAsedio(posicionADistancia1, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // unidades a distancia 2
        collection.add(new Object[]{armaDeAsedio, new Aldeano(posicionADistancia2), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Espadachin(posicionADistancia2,  Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Arquero(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new ArmaDeAsedio(posicionADistancia2, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // unidades a distancia 3
        collection.add(new Object[]{armaDeAsedio, new Aldeano(posicionADistancia3),DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Espadachin(posicionADistancia3, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Arquero(posicionADistancia3, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new ArmaDeAsedio(posicionADistancia3, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // unidades a distancia 4
        collection.add(new Object[]{armaDeAsedio, new Aldeano(posicionADistancia4),DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Espadachin(posicionADistancia4, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Arquero(posicionADistancia4, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new ArmaDeAsedio(posicionADistancia4, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // unidades a distancia 5
        collection.add(new Object[]{armaDeAsedio, new Aldeano(posicionADistancia5),DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Espadachin(posicionADistancia5, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new Arquero(posicionADistancia5, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});
        collection.add(new Object[]{armaDeAsedio, new ArmaDeAsedio(posicionADistancia5, Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_UNIDADES});

        // Construcciones a distancia 1
        collection.add(new Object[]{armaDeAsedio, new PlazaCentral(posicionADistancia1, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Cuartel(posicionADistancia1, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Castillo(posicionADistancia1, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        // Construcciones a distancia 2
        collection.add(new Object[]{armaDeAsedio, new PlazaCentral(posicionADistancia2, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Cuartel(posicionADistancia2, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Castillo(posicionADistancia2, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        // Construcciones a distancia 3
        collection.add(new Object[]{armaDeAsedio, new PlazaCentral(posicionADistancia3, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Cuartel(posicionADistancia3, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Castillo(posicionADistancia3, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        // Construcciones a distancia 4
        collection.add(new Object[]{armaDeAsedio, new PlazaCentral(posicionADistancia4, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Cuartel(posicionADistancia4, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Castillo(posicionADistancia4, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        // Construcciones a distancia 5
        collection.add(new Object[]{armaDeAsedio, new PlazaCentral(posicionADistancia5, Mockito.mock(IUnidadesPlazaCentralFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Cuartel(posicionADistancia5, Mockito.mock(IUnidadesCuartelFabrica.class)), DANIO_ESPERADO_EDIFICIOS});
        collection.add(new Object[]{armaDeAsedio, new Castillo(posicionADistancia5, Mockito.mock(IUnidadesCastilloFabrica.class), Mockito.mock(IEstrategiaAtaque.class)), DANIO_ESPERADO_EDIFICIOS});

        return collection;

    }

    // Constructor
    public ArmaDeAsedioAtaqueEnRangoTest(ArmaDeAsedio armaDeAsedio, IAtacable atacado, int danioEsperado){
        this.armaDeAsedio = armaDeAsedio;
        this.atacado = atacado;
        this.danioEsperado = danioEsperado;
    }

    @Test
    public void atacar_DentroDelRangoDeAtaque_ProduceDanioEsperado(){

        // Arrange
        armaDeAsedio.montar();
        int vidaInicialAtacado = atacado.getVida();

        // Act
        armaDeAsedio.atacar(atacado);
        int vidaFinalAtacado = atacado.getVida();
        int danioProducido = vidaInicialAtacado - vidaFinalAtacado;

        // Assert
        Assert.assertEquals(danioEsperado, danioProducido);

    }
}
