package view;

import com.sun.javafx.geom.Dimension2D;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

public class OverlayPanel {
    
    private final Pane overlayPane;
    private  ProgressBar pb;
    private final int life;
    private final Image icon;

    public OverlayPanel(Pane overlayPane, Entities entity, int life) {
        this.overlayPane = overlayPane;
        this.life = life;
        this.icon = new Image(entity.getName() + "/icon.png", overlayPane.getPrefHeight()/6, overlayPane.getPrefHeight()/6, true, true);
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
    
    public void setProgressBar(final int newLife) {
        this.pb.setProgress((life%100)*newLife);
    }
    
}
