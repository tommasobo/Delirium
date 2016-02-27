package view.screens.sprites;

import javafx.scene.layout.Pane;
import view.configs.Actions;
import view.utilities.ControlComunication;
import view.utilities.ViewPhysicalProperties;

/**
 * Methods available to dynamic scenes to manage drawn entities.
 */
public interface SpriteManager {
    /**
     * Track a new entity and initialize it.
     * 
     * @param addedEntity
     *            Informations about the new entity.
     * @throws IllegalArgumentException
     *             If the entity was already tracked.
     */
    void addSprite(final ControlComunication addedEntity);

    /**
     * Update an entity position and, if the entity is animated, his animation.
     * 
     * @param code
     *            The entity ID
     * @param action
     *            The entity new action.
     * @param properties
     *            The entity position and direction.
     * @throws IllegalArgumentException
     *             If the entity was not in tracking.
     */
    void updateSpriteState(final int code, final Actions action, final ViewPhysicalProperties properties);

    /**
     * Check if an entity is already tracked.
     * 
     * @param code
     *            The entity ID
     * @return True if it's tracked, false otherwise.
     */
    boolean isTracked(final int code);

    /**
     * Pause the animation of all animated entities.
     */
    void pauseAllSprites();

    /**
     * Resume the animation of all paused animated entities.
     */
    void resumeAllSprites();

    /**
     * Get the Pane where entities are represented.
     * 
     * @return The Entities Pane.
     */
    Pane getEntitiesPane();

}