package view;

import java.util.Map;

import control.Control;
import control.Dimension;
import control.Pair;

public interface ViewController {
    
    void updateScene(Map<Integer, Pair<Entities, Pair<Integer,ViewPosition>>> entities);
    //manca la struttura delle impostazioni Pair<SceneType, Settings>
    void changeScene(final Pair<SceneType, Dimension> settings);
    //da aggiungere il listener come parametro
    void setListener(final Control listener);
    
}
