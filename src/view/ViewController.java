package view;

import java.util.List;

import control.Control;
import control.Pair;
import javafx.geometry.Dimension2D;

public interface ViewController {
    
    void updateScene(final List<ControlComunication> entities);
    //manca la struttura delle impostazioni Pair<SceneType, Settings>
    void changeScene(final Pair<SceneType, Dimension2D> settings);
    //da aggiungere il listener come parametro
    void setListener(final Control listener);
    
}
