package model;

import java.util.List;

public interface Model {

    void notifyEvent(final Directions direction);

    void notifyEvent(final Actions action);

    List<EntitiesInfo> updateArena();

    List<EntitiesInfoToControl> getState();

    void createArena(final List<EntitiesInfo> entitiesInfo);

    void putBullet(final List<EntitiesInfo> entitiesInfo);

}
