package view;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.util.Pair;

public class UpdatableSpriteImpl extends AbstractSprite implements UpdatableSprite {

    private Pair<Actions, Timeline> currentAnimation;
    
    public UpdatableSpriteImpl(final Entities entity, final Dimension2D dimension) {
        super(entity, dimension);
    }

    @Override
    public void initSprite(Actions action) {
        this.currentAnimation = new Pair<>(action, animate(action, Timeline.INDEFINITE));
    }

    @Override
    public void updateSprite(Actions action, int duration) {
        if (action != this.currentAnimation.getKey()) {
            this.currentAnimation.getValue().stop();
            this.currentAnimation = new Pair<>(action, animate(action, duration));
        }
        
    }
    
    @Override
    public void resetSprite() {
        this.currentAnimation.getValue().stop();
    }
    
    private Timeline animate(final Actions action, final int duration) {
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(duration);
        timeline.setAutoReverse(true);
        
        int cont = 0;
        
        for (final ImageView im : super.getResourcesManager().getResources(action)) {
            KeyFrame key= new KeyFrame(Duration.millis(cont), e -> {
                super.getSpritePane().getChildren().clear();
                super.getSpritePane().getChildren().add(im);
            });
            timeline.getKeyFrames().add(key);
            cont+=200;
        }
        
        timeline.play();
        return timeline;
    }

}
