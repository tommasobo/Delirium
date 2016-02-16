package view;

import java.util.List;
import java.util.Optional;

import control.Control;
import control.Pair;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

public class ViewControllerImpl implements ViewController {
    
    private final static ViewController singleton = new ViewControllerImpl();
    private static Stage primaryStage;
    private Optional<DynamicView> drawableView = Optional.empty();
    private Control listener;
    
    private ViewControllerImpl() {}
    
    public static ViewController getView() {
        return singleton;
    }
    
    public static void setStage(final Stage ps) {
        primaryStage = ps;
    }

    @Override
    public void updateScene(final List<ControlComunication> entities) {
        final DynamicView dv = this.drawableView.orElseThrow(IllegalStateException::new);
        dv.updateScene(entities);
    }
    
    
    public void changeScene(final Pair<SceneType, Dimension2D> settings) {
        this.drawableView = ViewFactory.createNewScene(primaryStage, this.listener, settings);
    }

    @Override
    public void setListener(final Control listener) {
        this.listener = listener;
    }

}
