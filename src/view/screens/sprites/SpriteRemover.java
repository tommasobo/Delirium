package view.screens.sprites;

/**
 * This method adds the possibility of removing an entity from screen and from
 * tracking.
 */
interface SpriteRemover {
    /**
     * Remove an entity from tracking and delete it from the screen.
     * 
     * @param toRemove
     *            The Entity to remove
     * @throws IllegalArgumentException
     *             If the entity was not tracked.
     */
    void removeSprite(final int toRemove);
}
