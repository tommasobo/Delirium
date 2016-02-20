package view;

import control.Control;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractGenericView implements GenericView {
    
    private final Stage stage;
    private final Control listener;
    private final Group root;
    private final Dimension2D sceneDimension;
    
    public AbstractGenericView(final Stage stage, final Control listener, final Dimension2D sceneDimension) {
        this.stage = stage;
        this.listener = listener;
        this.root = new Group();
        this.sceneDimension = sceneDimension;
    }
    
    public void initScene() {
        this.stage.setResizable(false);
        final Scene mainScene = new Scene(this.root, this.sceneDimension.getWidth(), this.sceneDimension.getHeight());
        mainScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        this.completeInitialization();
    }
    
    abstract protected void completeInitialization();
    
    @Override
    public void display() {
        stage.setScene(this.root.getScene());
    }
    
    protected Control getListener() {
        return this.listener;
    }

    protected Group getRoot() {
        return this.root;
    }

    protected Dimension2D getSceneDimension() {
        return this.sceneDimension;
    }

}
