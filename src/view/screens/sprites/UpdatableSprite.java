package view.screens.sprites;

import view.configs.Actions;
import view.configs.Directions;

interface UpdatableSprite extends Sprite {

    void updateSprite(final Actions action, final Directions direction);

    void pauseSpriteAnimation();

    void resumeSpriteAnimation();

}
