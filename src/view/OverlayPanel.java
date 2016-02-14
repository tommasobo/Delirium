package view;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

public class OverlayPanel {
    
    private final Pane overlayPane;
    private  ProgressBar pb;
    private final int life;
    private final Image im = new Image("icon.jpg");

    public OverlayPanel(Pane overlayPane, Entities entity, int life) {
        this.overlayPane = overlayPane;
        this.life = life;
    }
    
    public void initOverlay() {
        
        final ImageView iv = new ImageView(im);
        iv.setTranslateX(10);
        iv.setTranslateY(10);
        iv.setFitWidth(50);
        iv.setFitHeight(50);
        pb = new ProgressBar();
        pb.setProgress(1);
        pb.setTranslateX(61);
        pb.setTranslateY(10);
        overlayPane.getChildren().addAll(iv,pb);
    }
    
    public void setProgressBar(final int newLife) {
        pb.setProgress((life%100)*newLife);
    }
    
}
