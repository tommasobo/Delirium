package view;

import control.Control;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.stage.Stage;

public abstract class AbstractGenericView implements GenericView {
    
    private final Stage stage;
    private final Control listener;
    private final Group root;
    private final Dimension2D dimension;
    
    public AbstractGenericView(final Stage stage, final Control listener, final Dimension2D dimension) {
        this.stage = stage;
        this.listener = listener;
        this.root = new Group();
        this.dimension = dimension;
        this.stage.setResizable(false);
        
    }
    
    abstract public void initScene();
    
    @Override
    public void display() {
        stage.setScene(this.root.getScene());
        stage.centerOnScreen();
    }
    
    protected Stage getStage() {
        return this.stage;
    }
    
    public Control getListener() {
        return this.listener;
    }

    public Group getRoot() {
        return this.root;
    }

    public Dimension2D getDimension() {
        return this.dimension;
    }

}
