package view.screens.sprites;

import javafx.scene.layout.Pane;
import view.Actions;
import view.Directions;

interface Sprite {

    void initSprite(final Actions action, final Directions direction);

    Pane getSpritePane();

}
