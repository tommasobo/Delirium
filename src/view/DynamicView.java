package view;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;

public interface DynamicView {
    
    void updateScene(final Map<Entities, List<Pair<Integer, Position>>> entities);
    
}
