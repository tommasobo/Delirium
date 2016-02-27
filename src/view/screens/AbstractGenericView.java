package view.screens;

import control.Control;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class that implements GenericView interface and contains common
 * implementation elements of all types of scene.
 */
abstract class AbstractGenericView implements GenericView {

    private final Stage stage;
    private final Control listener;
    private final Group root;
    private final Dimension2D sceneDimension;

    AbstractGenericView(final Stage stage, final Control listener, final Dimension2D sceneDimension) {
        this.stage = stage;
        this.listener = listener;
        this.root = new Group();
        this.sceneDimension = sceneDimension;
    }

    @Override
    public void initScene() {
        if (this.root.getScene() != null) {
            throw new IllegalStateException("Init already been called");
        }
        this.stage.setResizable(false);
        final Scene mainScene = new Scene(this.root, this.sceneDimension.getWidth(), this.sceneDimension.getHeight());
        mainScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.completeInitialization();
    }

    /**
     * Complete the initialization dependently on the scene to draw.
     */
    protected abstract void completeInitialization();

    @Override
    public void display() {
        if (this.root.getScene() == null) {
            throw new IllegalStateException("You have to call the init first");
        }
        stage.setScene(this.root.getScene());
        stage.centerOnScreen();
    }

    /**
     * Get the controller listener.
     * 
     * @return Controller listener
     */
    protected Control getListener() {
        return this.listener;
    }

    /**
     * Get the main group of this scene.
     * 
     * @return The main group of this scene.
     */
    protected Group getRoot() {
        return this.root;
    }

    /**
     * Get the dimension of the scene.
     * 
     * @return The dimension of the scene
     */
    protected Dimension2D getSceneDimension() {
        return this.sceneDimension;
    }

}
