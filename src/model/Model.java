package model;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.PGActions;
import control.Pair;
import control.Position;
import control.Position.Dimension;

public interface Model {
    
    void notifyEvent(List<PGActions> actions);
    
    Map<Entities, List<Pair<Integer, Position>>> getState();

    void CreateArena(Map<Entities, List<Pair<Integer, Position>>> entities, Dimension dimensions);
    

}
