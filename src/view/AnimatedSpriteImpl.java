package view;

import control.Dimension;
import javafx.scene.layout.Pane;

public abstract class AnimatedSpriteImpl implements AnimatedSprite {
    
    protected final Pane entitiesPane;
    protected final Pane entityView;
    
    public AnimatedSpriteImpl(final Pane entitiesPane, final Dimension dimension) {
        this.entitiesPane = entitiesPane;
        this.entityView = new Pane();
        this.entityView.setPrefWidth(dimension.getWidth());
        System.out.println(dimension.getHeight());
        this.entityView.setPrefHeight(dimension.getHeight());
        this.entitiesPane.getChildren().add(this.entityView);
    }
    
    public Pane getPane() {
        return this.entityView;
    }
    
    public void kill() {
        this.entitiesPane.getChildren().remove(this.entityView);
    }
    
    abstract protected void getResources();
    
    abstract public void startAnimation(final ViewPosition.Directions dir);

}
