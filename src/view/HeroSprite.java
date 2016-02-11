package view;

import java.util.Optional;

import control.Dimension;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HeroSprite extends AnimatedSpriteImpl {
    
    private ImageView[] left;
    private ImageView[] right;
    private ImageView down;
    private ImageView up;
    protected Optional<Timeline> currentAnimation = Optional.empty();
    private Optional<ViewPosition.Directions> currentDirection = Optional.empty();

    public HeroSprite(final Pane entitiesPane, final Dimension dimension) {
        super(entitiesPane, dimension);
        this.getResources();
    }
    
    @Override
    protected void getResources() {
        this.left = new ImageView[]{ new ImageView(new Image("sx1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true)), new ImageView(new Image("sx2.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true))};
        this.right = new ImageView[]{ new ImageView(new Image("dx1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true)), new ImageView(new Image("dx2.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true))};
        this.down = new ImageView(new Image("fermo2.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true));
        this.up = new ImageView(new Image("fermo1.png" ,super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true));
    }

    @Override
    public void startAnimation(final ViewPosition.Directions dir) {
        
        if (!this.currentDirection.isPresent() || this.currentDirection.get() != dir) {
            if (this.currentAnimation.isPresent()) {
                this.currentAnimation.get().stop();
            }
            
            switch (dir) {
            case DOWN:
                this.staticImage(down);
                break;
            case LEFT:
                this.animate(left);
                break;
            case NONE:
                this.staticImage(down);
                break;
            case RIGHT:
                this.animate(right);
                break;
            case UP:
                this.staticImage(up);
                break;
            default:
                break;
            
            }
            
            this.currentDirection = Optional.of(dir);    
        }
        
        
    }
    
    private void animate(final ImageView...res) {
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        
        int duration = 0;
        
        for (final ImageView im : res) {
            KeyFrame key= new KeyFrame(Duration.millis(duration), e -> {
                super.entityView.getChildren().clear();
                super.entityView.getChildren().add(im);
            });
            timeline.getKeyFrames().add(key);
            duration+=500;
        }
        
        timeline.play();
        this.currentAnimation = Optional.of(timeline);
    }
    
    private void staticImage(final ImageView image) {
        super.entityView.getChildren().clear();
        super.entityView.getChildren().add(image);
        this.currentAnimation = Optional.empty();
    }

}
