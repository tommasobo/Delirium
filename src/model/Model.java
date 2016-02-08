package model;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;
import control.Dimension;

public interface Model {
    
    void notifyEvent(List<PGActions> actions);
    
    public void updateArena();
    
    Map<Entities, List<Pair<Integer, Position>>> getState();

    void createArena(Map<Entities, List<Pair<Integer, Position>>> entities, Dimension dimensions);
    
}
