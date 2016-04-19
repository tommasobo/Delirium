package view.screens.sprites;

import javafx.geometry.Dimension2D;
import view.configs.Actions;
import view.configs.Directions;

/**
 * This interface adds to Sprite interface the capabilities to update sprite's animation.
 */
interface UpdatableSprite extends Sprite {
    /**
     * This method changes this sprite's runnig animation.
     * 
     * @param action
     *            The new action to represent
     * @param direction
     *            Entity's new direction
     * @throws IllegalStateException
     *             If called before initialization
     * @throws IllegalArgumentException
     *             If the new action is not supported
     */
    void updateSprite(final Actions action, final Directions direction);

    /**
     * This method pauses sprite's animation if running.
     * 
     * @throws IllegalStateException
     *             If called before initialization
     */
    void pauseSpriteAnimation();

    /**
     * This method plays sprite's animation if paused.
     * 
     * @throws IllegalStateException
     *             If called before initialization
     */
    void resumeSpriteAnimation();
    void changeDimension(final Dimension2D newDim);

}
