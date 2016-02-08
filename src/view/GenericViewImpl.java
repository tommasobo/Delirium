package view;

import control.Control;
import control.Dimension;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GenericViewImpl implements GenericView {
    
    protected final Stage stage;
    protected final Control listener;
    protected Group root;
    
    public GenericViewImpl(final Stage stage, final Control listener) {
        this.stage = stage;
        this.listener = listener;
    }
    
    @Override
    public void initView(final Dimension dimension) {
        this.root = new Group();
        //dimensioni della scena da definire
        new Scene(this.root, dimension.getWidth(), dimension.getHeight());
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
