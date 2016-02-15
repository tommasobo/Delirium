package view;

import javafx.geometry.Dimension2D;
import javafx.scene.layout.Pane;

public abstract class AbstractSprite implements Sprite {
    
    private final Pane spritePane;
    private final ResourcesManager resources;

    public AbstractSprite(final Entities entity, final Dimension2D dimension) {
        this.resources = new ResourcesManagerImpl(entity, dimension);
        this.spritePane = new Pane();
        this.spritePane.setPrefWidth(dimension.getWidth());
        this.spritePane.setPrefHeight(dimension.getHeight());
    }

    @Override
    abstract public void initSprite(final Actions action, final Directions direction);


    @Override
    public Pane getSpritePane() {
        return this.spritePane;
    }
    
    protected ResourcesManager getResourcesManager() {
        return this.resources;
    }

}
