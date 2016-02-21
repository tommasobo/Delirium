package view;

import java.util.List;
import java.util.Optional;

import control.Control;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
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
    
    private final Pane overlayPane = new Pane();
    private final Dimension2D worldDimension;
    private SpriteManager spriteManager;
    private Optional<OverlayPanel> status = Optional.empty();
    private Optional<StackPane> pausePane = Optional.empty();
    
    
    public DynamicViewImpl(final Stage stage, final Control listener, final Dimension2D sceneDimension, final Dimension2D worldDimension) {
        super(stage, listener, sceneDimension);
        this.worldDimension = worldDimension;
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
    protected void completeInitialization() {
        
        if (AudioManager.getAudioManager().isAudioAvailable()) {
            AudioManager.getAudioManager().playTheme("Shots");
        }
        final Pane entitiesPane = new Pane();
        entitiesPane.setPrefSize(this.worldDimension.getWidth(), this.worldDimension.getHeight());
        entitiesPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        entitiesPane.setCache(true);
        entitiesPane.setCacheHint(CacheHint.QUALITY);
        this.overlayPane.setPrefSize(super.getSceneDimension().getWidth() + 2, super.getSceneDimension().getHeight());
        this.overlayPane.getChildren().addAll(new Rectangle(super.getSceneDimension().getWidth(), 0, 1, super.getSceneDimension().getHeight()), new Rectangle(-1, 0, 1, super.getSceneDimension().getHeight()));
        final InputFromUser ifu = new InputFromUser(super.getListener());
        super.getRoot().getScene().setOnKeyPressed(ifu);
        super.getRoot().getScene().setOnKeyReleased(ifu);
        super.getRoot().getChildren().add(entitiesPane);
        super.getRoot().getChildren().add(this.overlayPane);
        this.spriteManager = new SpriteManagerImpl(entitiesPane);
        this.backGround(entitiesPane);

    }
    
    private void backGround(Pane pane) {
        ImageView moon = new ImageView(new Image("images/moon.png", 200, 200, true, true));
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
            if (this.spriteManager.getEntitiesPane().getTranslateX() >= -(this.worldDimension.getWidth() - super.getSceneDimension().getWidth() - 1)) {
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
        this.spriteManager.pauseAllSprites();
        final StackPane pausePane = new StackPane();
        pausePane.setPrefSize(super.getSceneDimension().getWidth(), super.getSceneDimension().getHeight());
        pausePane.setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.65), CornerRadii.EMPTY, Insets.EMPTY)));
        pausePane.getChildren().add(new ButtonsPane(super.getListener()).getButtonPane());
        super.getRoot().getChildren().add(pausePane);
        this.pausePane = Optional.of(pausePane);
    }

    @Override
    public void playScene() {
        this.spriteManager.resumeAllSprites();
        super.getRoot().getChildren().remove(this.pausePane.get());
        this.pausePane = Optional.empty();
    }
    
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }
 
}
