package atenea.fiuba.algoIII.ageoOfEmpires.unitTests;

import modelo.edificios.PlazaCentral;
import modelo.posicion.Posicion;
import modelo.unidades.Aldeano;
import modelo.unidades.UnidadesFabrica;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PlazaCentralTest {

    private PlazaCentral crearPlazaCentral(){
        int vidaMaximaDePlazaCentral = 450;
        return new PlazaCentral(Mockito.mock(Posicion.class), new UnidadesFabrica());
    }

    @Test
    public void construirAldeano_DevuelveAldeano(){

        // Arrange
        PlazaCentral plazaCentral = this.crearPlazaCentral();

        // Act
        Aldeano aldeano = plazaCentral.construirAldeano(Mockito.mock(Posicion.class));

        // Assert
        Assert.assertNotNull(aldeano);

    }

    @Test
    public void obtenerCostoAldeano_Devuelve25(){

        // Arange
        PlazaCentral plazaCentral = this.crearPlazaCentral();
        int costoEsperadoDeAldeano = 25;

        // Act
        int costoObtenidoDeAldeano = plazaCentral.obtenerCostoAldeano();

        // Assert
        Assert.assertEquals(costoEsperadoDeAldeano, costoObtenidoDeAldeano);

    }

}
