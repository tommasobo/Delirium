package view.screens.sprites;

import javafx.scene.layout.Pane;
import view.configs.Actions;
import view.configs.Directions;

/**
 * Basic methods available to create a sprite.
 */
interface Sprite {
    /**
     * This method initializes the sprite searching for necessaries res and creating needed
     * structure in order to support animated sprites and composed ones. This
     * method can only be called once.
     * 
     * @param action
     *            Entity's initial action.
     * @param direction
     *            Entity's initial direction.
     * @throws IllegalStateException
     *             If initialization was already called.
     */
    void initSprite(final Actions action, final Directions direction);

    /**
     * This method returns the pane used to represent the sprite.
     * 
     * @return JavaFX Sprite's pane.
     */
    Pane getSpritePane();

}
