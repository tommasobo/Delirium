package view;

import java.util.Optional;

import control.Dimension;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MovableSprite extends AnimatedSpriteImpl {
    
    private ImageView[] left;
    private ImageView[] right;
    private ImageView[] down;
    private ImageView[] up;
    private ImageView[] stationary;
    private Optional<Timeline> currentAnimation = Optional.empty();
    private Optional<ViewPosition> currentPosition = Optional.empty();
    private boolean still = false;

    public MovableSprite(final Pane entitiesPane, final Dimension dimension, final Entities entity) {
        super(entitiesPane, dimension, entity);
        this.getResources();
    }
    //creare il cercatore di risorse
    private void getResources() {
        
        this.left = new ImageView[]{ 
                new ImageView(new Image("sx1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true)), 
                new ImageView(new Image("sx2.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true))
        };
        this.right = new ImageView[]{ 
                new ImageView(new Image("dx1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true)), 
                new ImageView(new Image("dx2.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true))
        };
        this.up = new ImageView[]{ 
                new ImageView(new Image("fermo1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true)), 
                new ImageView(new Image("fermo1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true))
        };
        this.down = new ImageView[]{ 
                new ImageView(new Image("fermo1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true)), 
                new ImageView(new Image("fermo1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true))
        };
        this.stationary = new ImageView[]{ 
                new ImageView(new Image("fermo1.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true)), 
                new ImageView(new Image("fermo2.png", super.entityView.getPrefWidth(), super.entityView.getPrefHeight(), true, true))
        };
        
    }

    @Override
    public void updateSprite(final ViewPosition newPosition) {
        
        if (!this.currentPosition.isPresent()) {
            this.currentPosition = Optional.of(newPosition);
        }
        if (!this.currentPosition.get().equals(newPosition)) {
            if (!(!this.currentPosition.get().getPoint().equals(newPosition.getPoint()) && currentPosition.get().getDirection() == newPosition.getDirection())) {
                if (this.currentAnimation.isPresent()) {
                    this.currentAnimation.get().stop();
                }
                
                switch (newPosition.getDirection()) {
                case DOWN:
                    this.currentAnimation = Optional.of(this.animate(down));
                    break;
                case LEFT:
                    this.currentAnimation = Optional.of(this.animate(left));
                    break;
                case RIGHT:
                    this.currentAnimation = Optional.of(this.animate(right));
                    break;
                case UP:
                    this.currentAnimation = Optional.of(this.animate(up));
                    break;
                default:
                    break;
                }
                
            }
            this.currentPosition = Optional.of(newPosition);
            this.still = false;
            
        } else {
            if (!still && this.currentAnimation.isPresent()) {
                this.currentAnimation.get().stop();
            } else if (!still) {
                this.currentAnimation = Optional.of(this.animate(stationary));
            }
            this.still = true;
        }
    }
    
    //creare classe animator
    private Timeline animate(final ImageView...res) {
        
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
            duration+=200;
        }
        
        timeline.play();
        return timeline;
    }

}
