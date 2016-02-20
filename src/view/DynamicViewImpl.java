package view;

import java.util.List;
import java.util.Optional;

import control.Control;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DynamicViewImpl extends AbstractGenericView implements DynamicView {
    
    private SpriteManager spriteManager;
    private final Pane overlayPane = new Pane();
    private Optional<OverlayPanel> status = Optional.empty();
    private Optional<StackPane> pausePane = Optional.empty();
    
    public DynamicViewImpl(final Stage stage, final Control listener, final Dimension2D dimension) {
        super(stage, listener, dimension);
    }
    
    @Override
    public void updateScene(final List<ControlComunication> entities) {
        
        entities.stream().forEach(k -> {
            
            Platform.runLater(() -> {
                
                if(!this.spriteManager.isTracked(k.getCode())) {
                    this.spriteManager.addSprite(k);
                }
                this.spriteManager.updateSpriteState(k.getCode(), k.getAction(), k.getProperty());
                
                if (k.getCode() == 0) {
                    if (!status.isPresent()) {
                        this.status = Optional.of(new OverlayPanel(this.overlayPane, k.getEntity(), k.getLife()));
                        this.status.get().initOverlay();
                    }
                    moveScene(k.getProperty());
                    status.get().setProgressBar(k.getLife());
                }
            });
            
        });
    }

    @Override
    public void initScene() {
        new Scene(super.getRoot(), 500, super.getDimension().getHeight());
        super.getRoot().getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        final Pane entitiesPane = new Pane();
        entitiesPane.setPrefSize(super.getDimension().getWidth(), super.getDimension().getHeight());
        this.overlayPane.setPrefSize(super.getRoot().getScene().getWidth() + 2, super.getRoot().getScene().getHeight());
        entitiesPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        entitiesPane.setCache(true);
        entitiesPane.setCacheHint(CacheHint.QUALITY);
        this.overlayPane.getChildren().addAll(new Rectangle(super.getRoot().getScene().getWidth(), 0, 1, super.getRoot().getScene().getHeight()), new Rectangle(-1, 0, 1, super.getRoot().getScene().getHeight()));
        final InputFromUser ifu = new InputFromUser(super.getListener());
        super.getRoot().getScene().setOnKeyPressed(ifu);
        super.getRoot().getScene().setOnKeyReleased(ifu);
        super.getRoot().getChildren().add(entitiesPane);
        super.getRoot().getChildren().add(this.overlayPane);
        this.spriteManager = new SpriteManagerImpl(entitiesPane);
        this.backGround(entitiesPane);
        pauseScene();
        
    }
    
    
    private void backGround(Pane pane) {
        ImageView moon = new ImageView(new Image("moon.png", 200, 200, true, true));
        TranslateTransition translateTransition = new TranslateTransition(
            Duration.millis(4000), moon);
        translateTransition.setFromX(-300);
        translateTransition.setToX(1500);
        translateTransition.setCycleCount(Timeline.INDEFINITE);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
        pane.getChildren().add(moon);

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

    @Override
    public void pauseScene() {
        this.spriteManager.pauseAllSprite();
        final StackPane pausePane = new StackPane();
        pausePane.setPrefSize(500, super.getDimension().getHeight());
        pausePane.setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.65), CornerRadii.EMPTY, Insets.EMPTY)));
        pausePane.getChildren().add(new ButtonsPane(super.getListener()).getButtonPane());
        super.getRoot().getChildren().add(pausePane);
        this.pausePane = Optional.of(pausePane);
    }

    @Override
    public void playScene() {
        this.spriteManager.playAllSprite();
        super.getRoot().getChildren().remove(this.pausePane.get());
        this.pausePane = Optional.empty();
    }
 
}
