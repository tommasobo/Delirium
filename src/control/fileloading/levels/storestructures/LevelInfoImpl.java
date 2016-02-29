package control.fileloading.levels.storestructures;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import utility.Dimension;

/**
 * Class that contains all informations about a playable level.
 * 
 * @author Matteo Magnani
 *
 */
public class LevelInfoImpl implements LevelInfo {
    private final List<EntitiesInfoStoreImpl> entities;
    private final Dimension levelDimension;

    /**
     * 
     * @param entities
     *            List of level's entities
     * @param levelDimension
     *            The level dimension
     * @throws IOException
     */
    public LevelInfoImpl(final List<EntitiesInfoStoreImpl> entities, final Dimension levelDimension)
            throws IOException {
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
