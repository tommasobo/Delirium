package view.screens.sprites;

import javafx.geometry.Dimension2D;
import javafx.scene.layout.Pane;
import view.configs.Actions;
import view.configs.Directions;
import view.configs.Entities;
import view.utilities.ResourcesManager;

/**
 * Abstract implementation of Sprite interface.
 */
abstract class AbstractSprite implements Sprite {

    private final Pane spritePane;
    private final Entities entity;
    private final int code;
    private final ResourcesManager resources;

    AbstractSprite(final Entities entity, final int code, final Dimension2D dimension) {
        this.spritePane = new Pane();
        this.spritePane.setPrefWidth(dimension.getWidth());
        this.spritePane.setPrefHeight(dimension.getHeight());
        this.entity = entity;
        this.code = code;
        this.resources = ResourcesManager.getResourceManager();
    }

    @Override
    public abstract void initSprite(final Actions action, final Directions direction);

    @Override
    public Pane getSpritePane() {
        return this.spritePane;
    }

    /**
     * Check if an action passed in input is between entity's allowed ones.
     * 
     * @param action
     *            The input action
     * @throws IllegalStateException
     *             If the action is not supported for this entity.
     */
    protected void checkAction(final Actions action) {
        if (!this.entity.getAllowedActions().contains(action)) {
            throw new IllegalArgumentException("Action not allowed : " + entity.getName() + "-" + action.getString());
        }
    }

    /**
     * Get the resource manager.
     * 
     * @return The resource manager
     */
    protected ResourcesManager getResourcesManager() {
        return this.resources;
    }

    /**
     * Get the entity ID
     * 
     * @return The ID
     */
    protected int getCode() {
        return this.code;
    }

    /**
     * Get the entity represented by this sprite.
     * 
     * @return The entity
     */
    protected Entities getEntity() {
        return this.entity;
    }
}
