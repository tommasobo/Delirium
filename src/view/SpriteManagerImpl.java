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
    //cambiare i nomi ai code
    public void addSprite(final ControlComunication object) {
        if (object.getEntity().getCode() == 0 || object.getEntity().getCode() == 1) {
            this.updatableSprite.put(object.getCode(), new UpdatableSpriteImpl(object.getEntity(), object.getProperty().getDimension()));
        } else {
            this.nonUpdatableSprite.put(object.getCode(), new NonUpdatableSprite(object.getEntity(), object.getProperty().getDimension()));
        }
        this.getFromMaps(object.getCode()).initSprite(object.getAction(), object.getProperty().getDirection());
        this.updateSpriteState(object.getCode(), object.getAction(), object.getProperty());
        this.entitiesPane.getChildren().add(this.getFromMaps(object.getCode()).getSpritePane());
        
    }
    //to add death control
    public void updateSpriteState(final int code, final Actions action, final ViewPhysicalProperties properties) {
        if (this.updatableSprite.containsKey(code)) {
            this.updatableSprite.get(code).updateSprite(action, properties.getDirection(), Timeline.INDEFINITE);
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
