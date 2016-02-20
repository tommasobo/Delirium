package view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Pane;

public class SpriteManagerImpl implements SpriteManager, SpriteRemover {
    
    private final Pane entitiesPane;
    private final Map<Integer, Sprite> nonUpdatableSprite = new HashMap<>();
    private final Map<Integer, UpdatableSprite> updatableSprite = new HashMap<>();
    
    public SpriteManagerImpl(final Pane entitiesPane) {
        this.entitiesPane = entitiesPane;
    }
    
    @Override
    public void addSprite(final ControlComunication addedEntity) {
        if (addedEntity.getEntity().isAnimated()) {
            this.updatableSprite.put(addedEntity.getCode(), new UpdatableSpriteImpl(addedEntity.getEntity(), addedEntity.getCode(), addedEntity.getProperty().getDimension(), this));
        } else {
            this.nonUpdatableSprite.put(addedEntity.getCode(), new NonUpdatableSprite(addedEntity.getEntity(), addedEntity.getCode(), addedEntity.getProperty().getDimension()));
        }
        this.getFromMaps(addedEntity.getCode()).initSprite(addedEntity.getAction(), addedEntity.getProperty().getDirection());
        this.updateSpriteState(addedEntity.getCode(), addedEntity.getAction(), addedEntity.getProperty());
        this.entitiesPane.getChildren().add(this.getFromMaps(addedEntity.getCode()).getSpritePane());
        
    }
    
    @Override
    public void updateSpriteState(final int code, final Actions action, final ViewPhysicalProperties properties) {
        if (this.updatableSprite.containsKey(code)) {
            this.updatableSprite.get(code).updateSprite(action, properties.getDirection());
        }
        this.getFromMaps(code).getSpritePane().relocate(properties.getPoint().getX(), properties.getPoint().getY());
    }
    
    @Override
    public boolean isTracked(final int code) {
        return this.updatableSprite.containsKey(code) || this.nonUpdatableSprite.containsKey(code);
    }
    

    @Override
    public void pauseAllSprites() {
        this.updatableSprite.values().forEach(t -> t.pauseSpriteAnimation()); 
    }

    @Override
    public void resumeAllSprites() {
        this.updatableSprite.values().forEach(t -> t.resumeSpriteAnimation()); 
    }  
    
    @Override
    public Pane getEntitiesPane() {
        return this.entitiesPane;
    }
    
    @Override
    public void removeSprite(final int toRemove) {
        this.entitiesPane.getChildren().remove(this.getFromMaps(toRemove).getSpritePane());
        this.updatableSprite.remove(toRemove);
    }
    
    private Sprite getFromMaps(final int code) {
        if (updatableSprite.containsKey(code)) {
            return updatableSprite.get(code);
        }
        return nonUpdatableSprite.get(code);
    }
 
}
