package view;

import java.util.List;
import java.util.Optional;

import control.Control;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DynamicViewImpl extends AbstractGenericView implements DynamicView {
    
    private final Pane overlayPane = new Pane();
    private final Dimension2D worldDimension;
    private SpriteManager spriteManager;
    private Optional<OverlayPanel> status = Optional.empty();
    
    public DynamicViewImpl(final Stage stage, final Control listener, final Dimension2D sceneDimension, final Dimension2D worldDimension) {
        super(stage, listener, sceneDimension);
        this.worldDimension = worldDimension;
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

    }
    
    @Override
    public void updateScene(final List<ControlComunication> entities) {
        
        entities.stream().forEach(k -> {
            if(!this.spriteManager.isTracked(k.getCode())) {
                this.spriteManager.addSprite(k);
            }
            this.spriteManager.updateSpriteState(k.getCode(), k.getAction(), k.getProperty());
            if (k.getCode() == 0) {
                if (!status.isPresent()) {
                    this.status = Optional.of(new OverlayPanel(this.overlayPane, k.getEntity(), k.getLife()));
                    this.status.get().initOverlay();
                }
                if (AudioManager.getAudioManager().isAudioAvailable()) {
                    if (k.getAction() == Actions.JUMP || k.getAction() == Actions.MOVE || k.getAction() == Actions.SHOOT) {
                        AudioManager.getAudioManager().playClip(k.getAction());
                    }    
                }
                moveScene(k.getProperty());
                status.get().updateLife(k.getLife());
            }
        }); 
    }

    private void moveScene(final ViewPhysicalProperties position) {
       //riagiungere il +1 nell'if?
        if (position.getPoint().getX() + position.getDimension().getWidth() >= this.overlayPane.getChildren().get(0).getBoundsInParent().getMinX() - this.spriteManager.getEntitiesPane().getTranslateX() - super.getSceneDimension().getWidth()/3) {
            if (this.spriteManager.getEntitiesPane().getTranslateX() - position.getSpeed() >= -(this.worldDimension.getWidth() - super.getSceneDimension().getWidth())) {
                this.spriteManager.getEntitiesPane().setTranslateX(this.spriteManager.getEntitiesPane().getTranslateX() - position.getSpeed());
                if ((this.worldDimension.getWidth() - super.getSceneDimension().getWidth() + this.spriteManager.getEntitiesPane().getTranslateX()) <= position.getSpeed()) {
                    this.spriteManager.getEntitiesPane().setTranslateX(this.spriteManager.getEntitiesPane().getTranslateX() - (this.worldDimension.getWidth() - super.getSceneDimension().getWidth() + this.spriteManager.getEntitiesPane().getTranslateX()));
                }
            }  
        }
        if (position.getPoint().getX() <= this.overlayPane.getChildren().get(1).getBoundsInParent().getMaxX() -this.spriteManager.getEntitiesPane().getTranslateX() + super.getSceneDimension().getWidth()/3) {
            if (this.spriteManager.getEntitiesPane().getTranslateX() + position.getSpeed() <= -1) {
                this.spriteManager.getEntitiesPane().setTranslateX(this.spriteManager.getEntitiesPane().getTranslateX() + position.getSpeed());
                if (position.getSpeed() >= -(this.spriteManager.getEntitiesPane().getTranslateX())) {
                    this.spriteManager.getEntitiesPane().setTranslateX(0);
                }
            }
        }    
    }

    @Override
    public void pauseScene(final Notifications notification) {
        this.status.orElseThrow(IllegalStateException::new).addPausePane(super.getListener(), notification);
        this.spriteManager.pauseAllSprites();
    }

    @Override
    public void playScene() {
        this.status.orElseThrow(IllegalStateException::new).removePausePane();
        this.spriteManager.resumeAllSprites();
    }
    
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }
 
}