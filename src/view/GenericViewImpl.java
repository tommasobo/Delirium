package view;

import control.Control;
import control.Dimension;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GenericViewImpl implements GenericView {
    
    private final Stage stage;
    protected Group root;
    protected final Control listener;
    
    public GenericViewImpl(final Stage stage, final Control listener) {
        this.stage = stage;
        this.listener = listener;
    }
    
    @Override
    public void initView(final Dimension dimension) {
        this.root = new Group();
        new Scene(this.root);
        this.stage.setHeight(dimension.getHeight());
        this.stage.setWidth(dimension.getWidth());
        firstDraw();
    }
    
    @Override
    public void display() {
        stage.setScene(this.root.getScene());
        stage.centerOnScreen();
        stage.show();
    }
    
    abstract protected void firstDraw();

}
