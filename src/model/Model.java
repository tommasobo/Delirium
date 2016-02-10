package model;

import java.util.Map;

import control.Pair;
import control.Dimension;

public interface Model {
    
    void notifyEvent(ModelDirections action);
    
    public void updateArena();
    
    Map<Integer, Pair<Integer, ModelPosition>> getState();

    void createArena(Heroes hero, Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers,
            Dimension dimensions);
    
}
