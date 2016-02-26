package view.screens.sprites;

import view.Actions;
import view.Directions;

interface UpdatableSprite extends Sprite {

    void updateSprite(final Actions action, final Directions direction);

    void pauseSpriteAnimation();

    void resumeSpriteAnimation();

}
