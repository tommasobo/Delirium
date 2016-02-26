package view.screens.sprites;

import javafx.scene.layout.Pane;
import view.Actions;
import view.ControlComunication;
import view.ViewPhysicalProperties;

public interface SpriteManager {

    void addSprite(final ControlComunication addedEntity);

    void updateSpriteState(final int code, final Actions action, final ViewPhysicalProperties properties);

    boolean isTracked(final int code);

    void pauseAllSprites();

    void resumeAllSprites();

    Pane getEntitiesPane();

}