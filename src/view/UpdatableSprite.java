package view;

public interface UpdatableSprite extends Sprite{
    
    void updateSprite(final Actions action, final Directions direction);
    void pauseSpriteAnimation();
    void resumeSpriteAnimation();
    
}
