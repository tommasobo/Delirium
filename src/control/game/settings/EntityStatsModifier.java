package control.game.settings;

import control.fileloading.levels.storestructures.EntitiesInfoStore;

/**
 * Interface that declare methods for modify entities loaded from file.
 * 
 * @author Matteo Magnani
 *
 */
public interface EntityStatsModifier {
    /**
     * The method returns an entity's copy with its statistics modified.
     * 
     * @param entity
     *            The entity to modify
     * @return A copy of the entity with new statistics
     */
    EntitiesInfoStore modifyEntity(EntitiesInfoStore entity);
}
