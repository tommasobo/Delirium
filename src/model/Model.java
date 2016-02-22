package model;

import java.util.List;

public interface Model {
    
    void notifyEvent(Directions direction);
    
    void notifyEvent(Actions action);
    
    List<EntitiesInfo> updateArena();
    
    List<EntitiesInfoToControl> getState();

    void createArena(List<EntitiesInfo> entitiesInfo);
    
    void putBullet(List<EntitiesInfo> entitiesInfo);
    
}
