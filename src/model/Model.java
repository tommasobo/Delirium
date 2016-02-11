package model;

import java.util.Map;

import control.Pair;

public interface Model {
    
    void notifyEvent(ModelDirections action);
    
    public void updateArena();
    
    Map<Integer, Pair<Integer, ModelPosition>> getState();

    void createArena(Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers);
    
}
