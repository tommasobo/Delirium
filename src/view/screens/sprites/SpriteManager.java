package view.screens.sprites;

import javafx.scene.layout.Pane;
import view.configs.Actions;
import view.utilities.ControlCommunication;
import view.utilities.ViewPhysicalProperties;

/**
 * Methods available to dynamic scenes to manage drawn entities.
 */
public interface SpriteManager {
    /**
     * This method tracks a new entity and initializes it.
     * 
     * @param addedEntity
     *            Informations about the new entity.
     * @throws IllegalArgumentException
     *             If the entity was already tracked.
     */
    void addSprite(final ControlCommunication addedEntity);

    /**
     * This method updates an entity position and, if the entity is animated, his animation.
     * 
     * @param code
     *            Entity's ID
     * @param action
     *            Entity's new action.
     * @param properties
     *            Entity's position and direction.
     * @throws IllegalArgumentException
     *             If the entity was not in tracking.
     */
    void updateSpriteState(final int code, final Actions action, final ViewPhysicalProperties properties);

    /**
     * This method checks if an entity is already tracked.
     * 
     * @param code
     *            Entity's ID
     * @return True if it's tracked, false otherwise.
     */
    boolean isTracked(final int code);

    /**
     * This method pauses the animation of all animated entities.
     */
    void pauseAllSprites();

    /**
     * This method resumes the animation of all paused animated entities.
     */
    void resumeAllSprites();

    /**
     * This method returns the Pane where entities are represented.
     * 
     * @return Entities' Pane.
     */
    Pane getEntitiesPane();

}