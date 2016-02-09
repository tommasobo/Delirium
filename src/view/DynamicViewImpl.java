package view;

import java.util.HashMap;
import java.util.Map;

import control.Control;
import control.Pair;
import control.Position;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DynamicViewImpl extends GenericViewImpl implements DynamicView {
    
    private final Pane entitiesPane = new Pane();
    //utile per la visualizzazione del menu
    private final Pane overlayPane = new Pane();
    private final Map<Integer, ImageView> entitymap = new HashMap<>();
    private final Image lando = new Image("lando.png");
    
    Rectangle rightBounds;
    Rectangle leftBounds;
    
    public DynamicViewImpl(final Stage stage, final Control listener) {
        super(stage, listener);
    }

    @Override
    public void updateScene(Map<Integer, Pair<Entities, Pair<Integer,Position>>> entities) {
        
        addElements(entities);
        
        entities.keySet().forEach(k -> {
            
            final ImageView temp = this.entitymap.get(k);
            temp.relocate(entities.get(k).getY().getY().getPoint().getX(), entities.get(k).getY().getY().getPoint().getY());
            if (entities.get(k).getX() == Entities.JOYHERO) {
                moveScene(entities.get(k).getY().getY());
            }
            
        });
    }

    @Override
    protected void firstDraw() {
        
        new Scene(super.root, 1000, super.dim.getHeight());
        
        this.entitiesPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        this.entitiesPane.setCache(true);
        this.entitiesPane.setCacheHint(CacheHint.QUALITY);
        this.root.getChildren().add(this.entitiesPane);
        super.root.getScene().setOnKeyPressed(new InputFromUser(listener));
        //porchetto time
        rightBounds = new Rectangle();
        rightBounds.setHeight(root.getScene().getHeight());
        rightBounds.setWidth(5);
        rightBounds.setTranslateX(root.getScene().getWidth());
        leftBounds = new Rectangle();
        leftBounds.setHeight(root.getScene().getHeight());
        leftBounds.setWidth(5);
        leftBounds.setTranslateX(-5);
        overlayPane.getChildren().addAll(rightBounds, leftBounds);
        root.getChildren().add(overlayPane);
        
    }
    
    private void addElements(Map<Integer, Pair<Entities, Pair<Integer,Position>>> entities) {
        
        entities.keySet().forEach(k -> {
            final ImageView temp = new ImageView(lando);
            temp.setFitWidth(entities.get(k).getY().getY().getDimension().getWidth());
            temp.setFitHeight(entities.get(k).getY().getY().getDimension().getHeight());
            this.entitymap.merge(k, temp, (a,b) -> a);
        }); 
    }
    
    private void moveScene(final Position position) {
        
        if (this.entitymap.get(0).getBoundsInParent().getMaxX() >= rightBounds.getBoundsInParent().getMinX()) {
            this.entitiesPane.setTranslateX(this.entitiesPane.getTranslateX() - 10);
        }
        if (this.entitymap.get(0).getBoundsInParent().getMinX() <= leftBounds.getBoundsInParent().getMaxX()) {
            this.entitiesPane.setTranslateX(this.entitiesPane.getTranslateX() + 10);
        }    
        
    }
 
}
