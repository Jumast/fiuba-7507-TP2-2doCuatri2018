package vista.controles;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuBar;

public class BarraMenuControl extends MenuBar {

    public void OpcionSalirEventHandler(ActionEvent actionEvent){
        System.exit(0);
    }
}
