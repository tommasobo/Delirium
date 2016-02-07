package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GenericViewImpl implements GenericView {
    
    //da aggiungere il listener
    
    private final Stage stage;
    protected Group root;
    
    public GenericViewImpl(final Stage stage) {
        this.stage = stage;
    }
    
    @Override
    public void initView() {
        this.root = new Group();
        new Scene(this.root);
        firstDraw();
    }
    
    @Override
    public void display() {
        stage.setScene(this.root.getScene());
        stage.show();
    }
    
    abstract protected void firstDraw();

}
