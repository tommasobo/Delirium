package view.screens.sprites;

import view.configs.Actions;
import view.configs.Directions;

/**
 * Add to Sprite interface the capabilities to update the sprite animation.
 */
interface UpdatableSprite extends Sprite {
    /**
     * Change the running animation of this sprite.
     * 
     * @param action
     *            The new action to represent
     * @param direction
     *            The entity new direction
     * @throws IllegalStateException
     *             If called before initialization
     * @throws IllegalArgumentException
     *             If the new action is not supported
     */
    void updateSprite(final Actions action, final Directions direction);

    /**
     * Pause the sprite animation if running.
     * 
     * @throws IllegalStateException
     *             If called before initialization
     */
    void pauseSpriteAnimation();

    /**
     * Play the sprite animation if paused.
     * 
     * @throws IllegalStateException
     *             If called before initialization
     */
    void resumeSpriteAnimation();

}
