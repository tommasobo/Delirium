package view;

import java.util.Map;
import java.util.Optional;

import control.Control;
import control.Pair;
import control.ViewEvents;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DynamicViewImpl extends AbstractGenericView implements DynamicView {
    
    private SpriteManagerImpl spriteManager;
    private final Pane overlayPane = new Pane();
    private Optional<OverlayPanel> status = Optional.empty();  
    
    public DynamicViewImpl(final Stage stage, final Control listener, final Dimension2D dimension) {
        super(stage, listener, dimension);
    }

    @Override
    public void updateScene(Map<Integer, Pair<Entities, Pair<Integer,ViewPhysicalProperties>>> entities) {
        
        entities.keySet().forEach(k -> {
            
            Platform.runLater(() -> {
                
                if(!this.spriteManager.isTracked(k)) {
                    this.spriteManager.addSprite(k, entities.get(k).getX(), entities.get(k).getY().getY());
                }
                this.spriteManager.updateSpriteState(k, entities.get(k).getY().getY());
                
                if (entities.get(k).getX().getCode() == 0) {
                    if (!status.isPresent()) {
                        this.status = Optional.of(new OverlayPanel(this.overlayPane, entities.get(k).getX(), entities.get(k).getY().getX()));
                        this.status.get().initOverlay();
                    }
                    moveScene(entities.get(k).getY().getY());
                    status.get().setProgressBar(entities.get(k).getY().getX());
                }
            });
            
        });
    }

    @Override
    public void initScene() {
        
        new Scene(super.getRoot(), 500, super.getDimension().getHeight());
        
        final Pane entitiesPane = new Pane();
        entitiesPane.setPrefSize(super.getDimension().getWidth(), super.getDimension().getHeight());
        this.overlayPane.setPrefSize(super.getRoot().getScene().getWidth() + 2, super.getRoot().getScene().getHeight());
        //this.entitiesPane.setBackground(new Background(new BackgroundFill(Color.LIME, CornerRadii.EMPTY, Insets.EMPTY)));
        entitiesPane.setBackground(new Background(new BackgroundImage(new Image("nature.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(super.getDimension().getWidth(), super.getDimension().getHeight(), false, false, true, true))));
        entitiesPane.setCache(true);
        entitiesPane.setCacheHint(CacheHint.QUALITY);
        this.overlayPane.getChildren().addAll(new Rectangle(super.getRoot().getScene().getWidth(), 0, 1, super.getRoot().getScene().getHeight()), new Rectangle(-1, 0, 1, super.getRoot().getScene().getHeight()));
        
        super.getRoot().getScene().setOnKeyPressed(new InputFromUser(super.getListener()));
        super.getRoot().getScene().setOnKeyReleased(e -> super.getListener().notifyEvent(ViewEvents.STOPMOVEMENT));
        super.getRoot().getChildren().add(entitiesPane);
        super.getRoot().getChildren().add(this.overlayPane);
        this.spriteManager = new SpriteManagerImpl(entitiesPane);
        
    }
    
    
    private void moveScene(final ViewPhysicalProperties position) {
       
        if (position.getPoint().getX() + position.getDimension().getWidth() >= this.overlayPane.getChildren().get(0).getBoundsInParent().getMinX() - this.spriteManager.getEntitiesPane().getTranslateX() - 100) {
            if (this.spriteManager.getEntitiesPane().getTranslateX() >= -(super.getDimension().getWidth() - super.getRoot().getScene().getWidth() - 1)) {
                this.spriteManager.getEntitiesPane().setTranslateX(this.spriteManager.getEntitiesPane().getTranslateX() - position.getSpeed());
            }
        }
        if (position.getPoint().getX() <= this.overlayPane.getChildren().get(1).getBoundsInParent().getMaxX() -this.spriteManager.getEntitiesPane().getTranslateX() + 100) {
            if (this.spriteManager.getEntitiesPane().getTranslateX() <= -1) {
                this.spriteManager.getEntitiesPane().setTranslateX(this.spriteManager.getEntitiesPane().getTranslateX() + position.getSpeed());
            }    
        }    
    }
 
}
