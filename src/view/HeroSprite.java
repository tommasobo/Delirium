package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HeroSprite extends AnimatedSprite {
    
    private ImageView[] iv;

    public HeroSprite(Pane entitiesPane) {
        super(entitiesPane);
    }

    @Override
    void getResources() {
        this.iv = new ImageView[]{ new ImageView(new Image("lando.png")), new ImageView(new Image("prova.png"))};
        super.entityView.getChildren().add(this.iv[1]);
    }

    @Override
    void startAnimation() {
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyFrame key1= new KeyFrame(Duration.millis(200), e -> super.entityView.getChildren().setAll(this.iv[1]));
        KeyFrame key2= new KeyFrame(Duration.millis(200), e -> super.entityView.getChildren().setAll(this.iv[0]));
        KeyFrame key3= new KeyFrame(Duration.millis(200), e -> super.entityView.getChildren().setAll(this.iv[1]));
        timeline.getKeyFrames().addAll(key1, key2, key3);
        timeline.play();
        
    }

}
