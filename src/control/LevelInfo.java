package control;

import java.util.LinkedList;
import java.util.List;

import utility.Dimension;

public class LevelInfo {
    private final List<EntitiesInfoStore> entities;
    private final Dimension levelDimension;

    public LevelInfo(List<EntitiesInfoStore> entities, Dimension levelDimension) {
        this.entities = entities;
        this.levelDimension = levelDimension;
    }

    public List<EntitiesInfoStore> getEntities() {
        return new LinkedList<>(this.entities);
    }

    public Dimension getLevelDimension() {
        return levelDimension;
    }
}
