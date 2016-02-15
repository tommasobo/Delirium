package view;

import javafx.scene.layout.Pane;

public interface Sprite {
    
    void initSprite(final Actions action, final Directions direction);
    Pane getSpritePane();
    
}
