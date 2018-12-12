package vista.utilidades;

import javafx.scene.media.AudioClip;

import java.net.URL;

public class ReproductorDeSonido implements IReproductorDeSonido {

    private String nombreDeArchivo;

    public ReproductorDeSonido(String nombreDeArchivo){
        this.nombreDeArchivo = nombreDeArchivo;
    }

    private void reproducir(String nombreDeArchivo){

        try
        {

            String f = String.format("/vista/sonidos/%s", nombreDeArchivo);
            URL path = getClass().getResource(f);
            AudioClip ac = new AudioClip(path.toString());
            ac.play();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reproducirSonido() {
        this.reproducir(this.nombreDeArchivo);
    }
}
