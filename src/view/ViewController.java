package view;

import java.util.List;
import java.util.Map;

import control.Control;
import control.Dimension;
import control.Entities;
import control.Pair;
import control.Position;

public interface ViewController {
    
    void updateScene(Map<Entities,List<Pair<Integer,Position>>> entities);
    //manca la struttura delle impostazioni Pair<SceneType, Settings>
    void changeScene(final Pair<SceneType, Dimension> settings);
    //da aggiungere il listener come parametro
    void setListener(final Control listener);
    
}
