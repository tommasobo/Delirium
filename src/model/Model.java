package model;

import java.util.List;
import java.util.Map;

import control.Pair;

public interface Model {
    
    void notifyEvent(Directions action);
    
    public void updateArena();
    
    List<EntitiesInfo> getState();

    void createArena(List<EntitiesInfo> entitiesInfo);
    
}
