package view.screens.sprites;

import javafx.scene.layout.Pane;
import view.configs.Actions;
import view.configs.Directions;

/**
 * Basic methods available to create a sprite.
 */
interface Sprite {
    /**
     * Initialize the sprite searching for necessaries res and creating needed
     * structure in order to support animated sprites and composed ones. This
     * method can only be called once.
     * 
     * @param action
     *            The initial action of the entity.
     * @param direction
     *            The initial direction of the entity.
     * @throws IllegalStateException
     *             If initialization was already called.
     */
    void initSprite(final Actions action, final Directions direction);

    /**
     * Get the pane used to represent the sprite.
     * 
     * @return JavaFX Sprite pane.
     */
    Pane getSpritePane();

}
