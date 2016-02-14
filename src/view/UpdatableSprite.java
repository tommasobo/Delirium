package view;

public interface UpdatableSprite extends Sprite{
    
    void updateSprite(final Actions action, final int duration);
    void resetSprite();
    
}
