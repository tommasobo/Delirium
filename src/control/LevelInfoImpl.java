package control;

import java.util.LinkedList;
import java.util.List;

import utility.Dimension;

public class LevelInfoImpl implements LevelInfo {
    private final List<EntitiesInfoStore> entities;
    private final Dimension levelDimension;

    public LevelInfoImpl(final List<EntitiesInfoStore> entities, final Dimension levelDimension) {
        this.entities = entities;
        this.levelDimension = levelDimension;
    }

    
    @Override
    public List<EntitiesInfoStore> getEntities() {
        return new LinkedList<>(this.entities);
    }

    
    @Override
    public Dimension getLevelDimension() {
        return levelDimension;
    }
}
