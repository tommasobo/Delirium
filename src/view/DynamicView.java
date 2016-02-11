package view;

import java.util.Map;

import control.Pair;

public interface DynamicView {
    
    void updateScene(final Map<Integer, Pair<Entities, Pair<Integer,ViewPhysicalProperties>>> entities);
    
}
