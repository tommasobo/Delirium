package view;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public abstract class AnimatedSprite {
    
    protected final Pane entitiesPane;
    protected final Group entityView;
    
    public AnimatedSprite(final Pane entitiesPane) {
        this.entitiesPane = entitiesPane;
        this.entityView = new Group();
        this.entitiesPane.getChildren().add(this.entityView);
    }
    
    public Group getGroup() {
        return this.entityView;
    }
    
    public void kill() {
        this.entitiesPane.getChildren().remove(this.entityView);
    }
    
    abstract void getResources();
    
    abstract void startAnimation();

}
