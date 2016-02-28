package view.screens;

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
import javafx.scene.text.Text;
import view.configs.Actions;
import view.configs.Entities;
import view.configs.Notifications;
import view.utilities.AudioManager;

/**
 * This class handles the overlay elements present during the game.
 */
class OverlayPanel {

    private static final int LAYOUTFACTOR = 6;
    private final Pane overlayPane;
    private final double totalLife;
    private double life;
    private final Image icon;
    private Optional<ProgressBar> pb = Optional.empty();
    private Optional<StackPane> pausePane = Optional.empty();

    /**
     * OverlyPane Constructor
     * 
     * @param overlayPane
     *            The pane where to draw overlay elements.
     * @param entity
     *            The main character in the game.
     * @param life
     *            The main character life.
     */
    OverlayPanel(final Pane overlayPane, final Entities entity, final int life) {
        this.overlayPane = overlayPane;
        this.totalLife = life;
        this.life = life;
        this.icon = new Image("images/" + entity.getName() + "/icon.png", overlayPane.getPrefHeight() / LAYOUTFACTOR,
                overlayPane.getPrefHeight() / LAYOUTFACTOR, true, true);
    }

    /**
     * This method initializes the panel, drawing the main character icon and the life
     * bar. This method can only be called once.
     * 
     * @throws IllegalStateException
     *             If the panel was already initialized.
     */
    public void initOverlay() {
        if (this.pb.isPresent()) {
            throw new IllegalStateException("Init already been called");
        }
        final ImageView iv = new ImageView(icon);
        iv.setFitWidth(this.icon.getRequestedWidth());
        iv.setFitHeight(this.icon.getRequestedHeight());
        iv.setTranslateX(LAYOUTFACTOR * 2);
        iv.setTranslateY(LAYOUTFACTOR * 2);
        this.pb = Optional.of(new ProgressBar());
        this.pb.get().setStyle("-fx-accent: #7d0303;");
        this.pb.get().setProgress(1);
        this.pb.get().setTranslateX(overlayPane.getPrefHeight() / LAYOUTFACTOR + LAYOUTFACTOR * 3);
        this.pb.get().setTranslateY(LAYOUTFACTOR * 2);
        overlayPane.getChildren().addAll(iv, pb.get());
    }

    /**
     * This method updates life progress bar.
     * 
     * @param newLife
     *            Life's new value.
     * @throws IllegalStateException
     *             If called before initialization.
     */
    public void updateLife(final int newLife) {
        this.pb.orElseThrow(() -> new IllegalStateException("You have to call init first"))
                .setProgress((double) newLife / this.totalLife);
        if (AudioManager.getAudioManager().isAudioAvailable() && this.life != newLife) {
            AudioManager.getAudioManager().playClip(Actions.DEATH);
        }
        this.life = newLife;
    }

    /**
     * This method shows a ButtonPane on the overlay Pane by printing a message on the screen.
     * 
     * @param listener
     *            The control listener used to create a new ButtonsPane
     * @param notification
     *            The state that defines the message on the screen
     * @throws IllegalStateException
     *             If called when a PausePane is already present.
     */
    public void addPausePane(final Control listener, final Notifications notification) {
        if (this.pausePane.isPresent()) {
            throw new IllegalStateException("Pause pane already present");
        }
        final StackPane vpausePane = new StackPane();
        vpausePane.setPrefSize(this.overlayPane.getPrefWidth(), this.overlayPane.getPrefHeight());
        vpausePane.setBackground(
                new Background(new BackgroundFill(new Color(0, 0, 0, 0.65), CornerRadii.EMPTY, Insets.EMPTY)));
        final Text txt = new Text(notification.getToShow());
        txt.setId("text1");
        vpausePane.getChildren().addAll(txt, new ButtonsPane(listener).getButtonPane(this.overlayPane.getPrefWidth()));
        this.overlayPane.getChildren().add(vpausePane);
        this.pausePane = Optional.of(vpausePane);
    }

    /**
     * This method removes the pause pane.
     * 
     * @throws IllegalStateException
     *             If no pausePane is present.
     */
    public void removePausePane() {
        this.overlayPane.getChildren()
                .remove(this.pausePane.orElseThrow(() -> new IllegalStateException("No pause pane to remove")));
        this.pausePane = Optional.empty();
    }

}
