package model;

import java.util.Map;

import control.Pair;
import control.Position;
import control.Dimension;

public interface Model {
    
    void notifyEvent(PGActions action);
    
    public void updateArena();
    
    Map<Integer, Pair<Integer, Position>> getState();

    void createArena(Heroes hero, Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers,
            Dimension dimensions);
    
}
