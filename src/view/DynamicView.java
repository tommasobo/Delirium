package view;

import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;

public interface DynamicView {
    
    void updateScene(final Map<Integer, Pair<Entities, Pair<Integer,Position>>> entities);
    
}
