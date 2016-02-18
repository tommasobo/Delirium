package view;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.util.Pair;

public class UpdatableSpriteImpl extends AbstractSprite implements UpdatableSprite {

    private Pair<String, Timeline> currentAnimation;
    
    public UpdatableSpriteImpl(final Entities entity, final Dimension2D dimension) {
        super(entity, dimension);
    }

    @Override
    public void initSprite(final Actions action, final Directions direction) {
        super.checkAction(action);
        final String composedAction = this.composeAction(action, direction);
        this.currentAnimation = new Pair<>(composedAction, animate(composedAction, Timeline.INDEFINITE));
    }

    @Override
    public void updateSprite(final Actions action, final Directions direction) {
        super.checkAction(action);
        final String composedAction = this.composeAction(action, direction);
        if (!composedAction.equals(this.currentAnimation.getKey()) && (this.currentAnimation.getValue().cycleCountProperty().get() < 0 || (this.currentAnimation.getValue().cycleCountProperty().get() > 0 && this.currentAnimation.getValue().getStatus() != Status.RUNNING))) {
            this.currentAnimation.getValue().stop();
            this.currentAnimation = new Pair<>(composedAction, animate(composedAction, action.getDuration()));
        }
    }
    
    @Override
    public void pauseSprite() {
        this.currentAnimation.getValue().pause();
    }
    
    private Timeline animate(final String composedAction, final int duration) {
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(duration);
        timeline.setAutoReverse(false);
        
        int cont = 0;
        
        for (final ImageView im : super.getResourcesManager().getResources(composedAction)) {
            KeyFrame key= new KeyFrame(Duration.millis(cont), e -> {
                super.getSpritePane().getChildren().clear();
                super.getSpritePane().getChildren().add(im);
            });
            timeline.getKeyFrames().add(key);
            cont+=125;
        }
        
        timeline.play();
        return timeline;
    }
    
    private String composeAction(final Actions action, final Directions direction) {
        return action.getString() + "-" + direction.getName();
    }

}
