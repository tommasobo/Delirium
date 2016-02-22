package view;

import java.util.Optional;

import control.Control;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class OverlayPanel {
    
    private final Pane overlayPane;
    private final double life;
    private final Image icon;
    private  ProgressBar pb;
    private Optional<StackPane> pausePane = Optional.empty();

    public OverlayPanel(Pane overlayPane, Entities entity, int life) {
        this.overlayPane = overlayPane;
        this.life = life;
        this.icon = new Image("images/" + entity.getName() + "/icon.png", overlayPane.getPrefHeight()/6, overlayPane.getPrefHeight()/6, true, true);
    }
    
    public void initOverlay() {
        
        final ImageView iv = new ImageView(icon);
        iv.setFitWidth(this.icon.getRequestedWidth());
        iv.setFitHeight(this.icon.getRequestedHeight());
        iv.setTranslateX(10);
        iv.setTranslateY(10);
        this.pb = new ProgressBar();
        this.pb.setStyle("-fx-accent: #7d0303;");
        this.pb.setProgress(1);
        this.pb.setTranslateX(overlayPane.getPrefHeight()/6 + 11);
        this.pb.setTranslateY(10);
        overlayPane.getChildren().addAll(iv,pb);
    }
    
    public void updateLife(final int newLife) {
        this.pb.setProgress((double)newLife/this.life);
    }
    
    public void addPausePane(final Control listener) {
        final StackPane pausePane = new StackPane();
        pausePane.setPrefSize(this.overlayPane.getPrefWidth(), this.overlayPane.getPrefHeight());
        pausePane.setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.65), CornerRadii.EMPTY, Insets.EMPTY)));
        pausePane.getChildren().add(new ButtonsPane(listener).getButtonPane());
        this.overlayPane.getChildren().add(pausePane);
        this.pausePane = Optional.of(pausePane);
    }
    
    public void removePausePane() {
        this.overlayPane.getChildren().remove(this.pausePane.orElseThrow(IllegalStateException::new));
        this.pausePane = Optional.empty();
    }
    
    //si puo aggiungere qui il messaggio di sconfitta/vittoria?
}
