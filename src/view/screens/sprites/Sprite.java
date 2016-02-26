package view.screens.sprites;

import javafx.scene.layout.Pane;
import view.configs.Actions;
import view.configs.Directions;

interface Sprite {

    void initSprite(final Actions action, final Directions direction);

    Pane getSpritePane();

}
