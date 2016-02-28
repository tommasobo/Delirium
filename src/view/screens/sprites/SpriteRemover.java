package view.screens.sprites;

/**
 * This interface adds the possibility of removing an entity from the screen and from
 * tracking.
 */
interface SpriteRemover {
    /**
     * This method removes an entity from tracking and deletes it from the screen.
     * 
     * @param toRemove
     *            The Entity to remove
     * @throws IllegalArgumentException
     *             If the entity was not tracked.
     */
    void removeSprite(final int toRemove);
}
