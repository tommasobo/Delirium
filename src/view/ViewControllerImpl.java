package view;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import control.Control;
import control.Dimension;
import control.Entities;
import control.Pair;
import control.Position;
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
    
    public static void setStage(Stage ps) {
        primaryStage = ps;
    }

    @Override
    public void updateScene(final Map<Entities, List<Pair<Integer, Position>>> entities) {
        final DynamicView dv = this.drawableView.orElseThrow(IllegalStateException::new);
        dv.updateScene(entities);
    }
    
    //stabilire la struttura di comunicazione
    public void changeScene(final Pair<SceneType, Dimension> settings) {
        this.drawableView = ViewFactory.createNewScene(primaryStage, this.listener, settings);
    }

    @Override
    public void setListener(final Control listener) {
        this.listener = listener;
    }

}
