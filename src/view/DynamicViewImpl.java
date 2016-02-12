package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import control.Control;
import control.Pair;
import control.ViewEvents;
import javafx.application.Platform;
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

public class DynamicViewImpl extends GenericViewImpl implements DynamicView {
    //pannello che visualizza il mondo di gioco
    private final Pane entitiesPane = new Pane();
    //pannello contenente gli elementi di overlay 
    private final Pane overlayPane = new Pane();
    //contiene una lista degli elementi visualizzati al momento
    private final Map<Integer, AnimatedSprite> entitymap = new HashMap<>();
    private Optional<OverlayPanel> status = Optional.empty();  
    
    public DynamicViewImpl(final Stage stage, final Control listener) {
        super(stage, listener);
    }

    @Override
    public void updateScene(Map<Integer, Pair<Entities, Pair<Integer,ViewPhysicalProperties>>> entities) {
        
        addElements(entities);
        
        entities.keySet().forEach(k -> {
            
            Platform.runLater(() -> {
                final Pane temp = this.entitymap.get(k).getSpritePane();
                temp.relocate(entities.get(k).getY().getY().getPoint().getX(), entities.get(k).getY().getY().getPoint().getY());
                this.entitymap.get(k).updateSprite(entities.get(k).getY().getY());
                if (entities.get(k).getX() == Entities.JOYHERO) {
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
    protected void firstDraw() {
        
        new Scene(super.root, 500, super.dim.getHeight());
        
        this.entitiesPane.setPrefSize(super.dim.getWidth(), super.dim.getHeight());
        this.overlayPane.setPrefSize(super.root.getScene().getWidth() + 2, super.root.getScene().getHeight());
        //this.entitiesPane.setBackground(new Background(new BackgroundFill(Color.LIME, CornerRadii.EMPTY, Insets.EMPTY)));
        this.entitiesPane.setBackground(new Background(new BackgroundImage(new Image("nature.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(super.dim.getWidth(), super.dim.getHeight(), false, false, true, true))));
        this.entitiesPane.setCache(true);
        this.entitiesPane.setCacheHint(CacheHint.QUALITY);
        this.overlayPane.getChildren().addAll(new Rectangle(root.getScene().getWidth(), 0, 1, root.getScene().getHeight()), new Rectangle(-1, 0, 1, root.getScene().getHeight()));
        super.root.getScene().setOnKeyPressed(new InputFromUser(listener));
        super.root.getScene().setOnKeyReleased(e -> super.listener.notifyEvent(ViewEvents.STOPMOVEMENT));
        super.root.getChildren().add(this.entitiesPane);
        super.root.getChildren().add(this.overlayPane);
        
    }
    
    private void addElements(Map<Integer, Pair<Entities, Pair<Integer,ViewPhysicalProperties>>> entities) {
        
        entities.keySet().forEach(k -> {
            
            Platform.runLater(() -> {
                if (!this.entitymap.containsKey(k)) {
                    //aggiungere factory
                    final MovableSprite hs = new MovableSprite(this.entitiesPane, entities.get(k).getY().getY().getDimension(), entities.get(k).getX());
                    hs.updateSprite(entities.get(k).getY().getY());
                    this.entitymap.put(k, hs);
                }
            });

        }); 
    }
    
    private void moveScene(final ViewPhysicalProperties position) {
       
        if (this.entitymap.get(0).getSpritePane().getBoundsInParent().getMaxX() >= this.overlayPane.getChildren().get(0).getBoundsInParent().getMinX() -this.entitiesPane.getTranslateX() - 100) {
            if (this.entitiesPane.getTranslateX() >= -(super.dim.getWidth() - super.root.getScene().getWidth() - 1)) {
                this.entitiesPane.setTranslateX(this.entitiesPane.getTranslateX() - position.getSpeed());
            }
        }
        if (this.entitymap.get(0).getSpritePane().getBoundsInParent().getMinX() <= this.overlayPane.getChildren().get(1).getBoundsInParent().getMaxX() -this.entitiesPane.getTranslateX() + 100) {
            if (this.entitiesPane.getTranslateX() <= -1) {
                this.entitiesPane.setTranslateX(this.entitiesPane.getTranslateX() + position.getSpeed());
            }    
        }    
    }
 
}
