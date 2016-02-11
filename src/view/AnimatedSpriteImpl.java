package view;

import control.Dimension;
import javafx.scene.layout.Pane;

public abstract class AnimatedSpriteImpl implements AnimatedSprite {
    
    protected final Pane entitiesPane;
    protected final Pane entityView;
    protected final Entities entity;
    
    public AnimatedSpriteImpl(final Pane entitiesPane, final Dimension dimension, final Entities entity) {
        this.entity = entity;
        this.entitiesPane = entitiesPane;
        this.entityView = new Pane();
        this.entityView.setPrefWidth(dimension.getWidth());
        this.entityView.setPrefHeight(dimension.getHeight());
        this.entitiesPane.getChildren().add(this.entityView);
    }
    
    public Pane getSpritePane() {
        return this.entityView;
    }
    
    public void removeSprite() {
        this.entitiesPane.getChildren().remove(this.entityView);
    }
    
    abstract public void updateSprite(final ViewPhysicalProperties currentPosition);

}
