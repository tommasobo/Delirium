package view;

import javafx.geometry.Dimension2D;
import javafx.scene.layout.Pane;

public abstract class AbstractSprite implements Sprite {
    
    private final Pane spritePane;
    private final Entities entity;
    private final int code;
    private final ResourcesManager resources;

    public AbstractSprite(final Entities entity, final int code, final Dimension2D dimension) {
        this.spritePane = new Pane();
        this.spritePane.setPrefWidth(dimension.getWidth());
        this.spritePane.setPrefHeight(dimension.getHeight());
        this.entity = entity;
        this.code = code;
        this.resources = ResourcesManager.getResourceManager();
    }

    @Override
    abstract public void initSprite(final Actions action, final Directions direction);

    @Override
    public Pane getSpritePane() {
        return this.spritePane;
    }
     
    protected void checkAction(final Actions action) {
        if (!this.entity.getAllowedActions().contains(action)) {
            throw new IllegalArgumentException();
        }
    }
    
    protected ResourcesManager getResourcesManager() {
        return this.resources;
    }
    
    protected int getCode() {
        return this.code;
    }
    
    protected Entities getEntity() {
        return this.entity;
    }
}
