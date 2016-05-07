package view.screens;

import java.util.List;
import java.util.Optional;

import control.Control;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.configs.Music;
import view.configs.Notifications;
import view.screens.sprites.SpriteManager;
import view.screens.sprites.SpriteManagerImpl;
import view.utilities.AudioManager;
import view.utilities.ControlCommunication;
import view.utilities.ViewPhysicalProperties;

/**
 * GenericView that implements DynamicView interface and in this way it offers update
 * functionalities.
 */
class DynamicViewImpl extends AbstractGenericView implements DynamicView {

    private final Pane overlayPane = new Pane();
    private final Dimension2D worldDimension;
    private Optional<SpriteManager> spriteManager = Optional.empty();
    private Optional<OverlayPanel> status = Optional.empty();

    /**
     * DynamicViewImpl Constructor
     * 
     * @param stage
     *            Main stage of JavaFX application
     * @param listener
     *            Controller listener
     * @param sceneDimension
     *            Scene request dimension
     * @param worldDimension
     *            Drawn world request dimension
     */
    DynamicViewImpl(final Stage stage, final Control listener, final Dimension2D sceneDimension,
            final Dimension2D worldDimension) {
        super(stage, listener, sceneDimension);
        this.worldDimension = worldDimension;
    }

    @Override
    protected void completeInitialization() {

        if (AudioManager.getAudioManager().isAudioAvailable()) {
            AudioManager.getAudioManager().playTheme(Music.GAMETHEME);
        }
        final Pane entitiesPane = new Pane();
        final LinearGradient gradient2 = new javafx.scene.paint.LinearGradient(0, 0, 0, 1 , true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0.30, Color.BLACK),
                new Stop(0.5, Color.web("#330000")),
                new Stop(0.7, Color.web("#660000")),
                new Stop(0.80, Color.web("#990000")),
                new Stop(0.85, Color.web("#AA0000")),
              //new Stop(0.85, Color.web("#AA0000")),
        }); 
        final LinearGradient gradient = new javafx.scene.paint.LinearGradient(0, 0, 0, 1 , true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0.30, Color.BLACK),
                new Stop(0.5, Color.web("#330000")),
                new Stop(0.7, Color.web("#660000")),
                new Stop(0.80, Color.web("#990000")),
                new Stop(0.85, Color.web("#AA0000")),
        });  
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(-1);
        timeline.setAutoReverse(true);
        int cont = 0;
        final KeyFrame key = new KeyFrame(Duration.millis(cont), bx -> {
            entitiesPane.setBackground(new Background(new BackgroundFill(gradient2, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        cont += 1000;
        timeline.getKeyFrames().add(key);
        final KeyFrame key1 = new KeyFrame(Duration.millis(cont), bx -> {
            entitiesPane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        timeline.getKeyFrames().add(key1);
        cont += 1000;
        timeline.play();
        
        entitiesPane.setPrefSize(this.worldDimension.getWidth(), this.worldDimension.getHeight());
        
        entitiesPane.setCache(true);
        entitiesPane.setCacheHint(CacheHint.QUALITY);
        this.overlayPane.setPrefSize(super.getSceneDimension().getWidth() + 2, super.getSceneDimension().getHeight());
        this.overlayPane.getChildren()
                .addAll(new Rectangle(super.getSceneDimension().getWidth(), 0, 1,
                        super.getSceneDimension().getHeight()),
                new Rectangle(-1, 0, 1, super.getSceneDimension().getHeight()));
        final InputFromUser ifu = new InputFromUser(super.getListener());
        super.getRoot().getScene().setOnKeyPressed(ifu);
        super.getRoot().getScene().setOnKeyReleased(ifu);
        super.getRoot().getChildren().add(entitiesPane);
        super.getRoot().getChildren().add(this.overlayPane);
        this.spriteManager = Optional.of(new SpriteManagerImpl(entitiesPane));

    }

    @Override
    public void updateScene(final List<ControlCommunication> entities) {
        this.spriteManager.orElseThrow(() -> new IllegalStateException("You must call init first"));
        entities.stream().forEach(k -> {
            if (!this.spriteManager.get().isTracked(k.getCode())) {
                this.spriteManager.get().addSprite(k);
            }
            this.spriteManager.get().updateSpriteState(k.getCode(), k.getAction(), k.getProperty());
            if (k.getCode() == 0) {
                if (!status.isPresent()) {
                    this.status = Optional.of(new OverlayPanel(this.overlayPane, k.getEntity(), k.getLife()));
                    this.status.get().initOverlay();
                }
                if (AudioManager.getAudioManager().isAudioAvailable()
                        && AudioManager.getAudioManager().getAllowedActions().contains(k.getAction())) {
                    AudioManager.getAudioManager().playClip(k.getAction());
                }
                moveScene(k.getProperty());
                status.get().updateLife(k.getLife());
            }
        });
    }

    /**
     * This method changes the current visualized part of game world according to a given
     * position.
     * 
     * @param position
     *            The position to consider updating the camera point of view
     */
    private void moveScene(final ViewPhysicalProperties position) {
        this.spriteManager.orElseThrow(() -> new IllegalStateException("You must call init first"));
        final double maxTranslation = this.worldDimension.getWidth() - super.getSceneDimension().getWidth();
        final double translationOffset = super.getSceneDimension().getWidth() / 3;
        if (position.getPoint().getX()
                + position.getDimension().getWidth() >= this.overlayPane.getChildren().get(0).getBoundsInParent()
                        .getMinX() - this.spriteManager.get().getEntitiesPane().getTranslateX() - translationOffset
                && this.spriteManager.get().getEntitiesPane().getTranslateX()
                        - position.getSpeed() >= -maxTranslation) {
            this.spriteManager.get().getEntitiesPane()
                    .setTranslateX(this.spriteManager.get().getEntitiesPane().getTranslateX() - position.getSpeed());
            if ((maxTranslation + this.spriteManager.get().getEntitiesPane().getTranslateX()) <= position.getSpeed()) {
                this.spriteManager.get().getEntitiesPane().setTranslateX(-maxTranslation);
            }
        }
        if (position.getPoint().getX() <= this.overlayPane.getChildren().get(1).getBoundsInParent().getMaxX()
                - this.spriteManager.get().getEntitiesPane().getTranslateX() + translationOffset
                && this.spriteManager.get().getEntitiesPane().getTranslateX() + position.getSpeed() <= -1) {
            this.spriteManager.get().getEntitiesPane()
                    .setTranslateX(this.spriteManager.get().getEntitiesPane().getTranslateX() + position.getSpeed());
            if (position.getSpeed() >= -(this.spriteManager.get().getEntitiesPane().getTranslateX())) {
                this.spriteManager.get().getEntitiesPane().setTranslateX(0);
            }
        }
    }

    @Override
    public void pauseScene(final Notifications notification) {
        this.status.orElseThrow(() -> new IllegalStateException("You have to update the view at least once first"))
                .addPausePane(super.getListener(), notification);
        this.spriteManager.orElseThrow(() -> new IllegalStateException("You have to call init first"))
                .pauseAllSprites();
    }

    @Override
    public void playScene() {
        this.status.orElseThrow(() -> new IllegalStateException("You have to update the view at least once first"))
                .removePausePane();
        this.spriteManager.orElseThrow(() -> new IllegalStateException("You have to call init first"))
                .resumeAllSprites();
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

}