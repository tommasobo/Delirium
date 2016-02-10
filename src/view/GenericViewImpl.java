package view;

import control.Control;
import control.Dimension;
import javafx.scene.Group;
import javafx.stage.Stage;

public abstract class GenericViewImpl implements GenericView {
    
    protected final Stage stage;
    protected final Control listener;
    protected Group root;
    protected Dimension dim;
    
    public GenericViewImpl(final Stage stage, final Control listener) {
        this.stage = stage;
        this.listener = listener;
    }
    
    @Override
    public void initView(final Dimension dimension) {
        this.root = new Group();
        this.dim = dimension;
        //this.stage.setResizable(false);
        firstDraw();
    }
    
    @Override
    public void display() {
        stage.setScene(this.root.getScene());
        stage.centerOnScreen();
    }
    
    abstract protected void firstDraw();

}
