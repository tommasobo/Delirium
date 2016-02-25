package control;

import java.util.LinkedList;
import java.util.List;

import utility.Dimension;

public class LevelInfoImpl implements LevelInfo {
    private final List<EntitiesInfoStore> entities;
    private final Dimension levelDimension;

    public LevelInfoImpl(List<EntitiesInfoStore> entities, Dimension levelDimension) {
        this.entities = entities;
        this.levelDimension = levelDimension;
    }

    /* (non-Javadoc)
     * @see control.LevelInfo#getEntities()
     */
    @Override
    public List<EntitiesInfoStore> getEntities() {
        return new LinkedList<>(this.entities);
    }

    /* (non-Javadoc)
     * @see control.LevelInfo#getLevelDimension()
     */
    @Override
    public Dimension getLevelDimension() {
        return levelDimension;
    }
}
