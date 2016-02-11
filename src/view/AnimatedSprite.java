package view;

import javafx.scene.layout.Pane;

public interface AnimatedSprite {

    Pane getSpritePane();
    void updateSprite(final ViewPhysicalProperties currentPosition);
    void removeSprite();
    
}
