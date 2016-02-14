package view;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class SpriteManagerImpl {
    
    private final Pane entitiesPane;
    private final Map<Integer, Sprite> nonUpdatableSprite = new HashMap<>();
    private final Map<Integer, UpdatableSprite> updatableSprite = new HashMap<>();
    
    public SpriteManagerImpl(final Pane entitiesPane) {
        this.entitiesPane = entitiesPane;
    }
    
    public void addSprite(final int code, final Entities entity, final ViewPhysicalProperties properties) {
        if (entity.getCode() == 0 || entity.getCode() == 1) {
            this.updatableSprite.put(code, new UpdatableSpriteImpl(entity, properties.getDimension()));
        } else {
            this.nonUpdatableSprite.put(code, new NonUpdatableSprite(entity, properties.getDimension()));
        }
        this.getFromMaps(code).initSprite(properties.getAction());
        this.updateSpriteState(code, properties);
        this.entitiesPane.getChildren().add(this.getFromMaps(code).getSpritePane());
        
    }
    //to add death control
    public void updateSpriteState(final int code, final ViewPhysicalProperties properties) {
        if (this.updatableSprite.containsKey(code)) {
            this.updatableSprite.get(code).updateSprite(properties.getAction(), Timeline.INDEFINITE);
        }
        this.getFromMaps(code).getSpritePane().relocate(properties.getPoint().getX(), properties.getPoint().getY());
    }
    
    public boolean isTracked(final int code) {
        return this.updatableSprite.containsKey(code) || this.nonUpdatableSprite.containsKey(code);
    }
    
    public Pane getEntitiesPane() {
        return this.entitiesPane;
    }
    
    private Sprite getFromMaps(final int code) {
        if (updatableSprite.containsKey(code)) {
            return updatableSprite.get(code);
        }
        return nonUpdatableSprite.get(code);
    }
}
