package view;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import control.Control;
import control.Entities;
import control.Pair;
import control.Position;
import javafx.stage.Stage;

public class ViewControllerImpl implements ViewController {
    
    private final static ViewController singleton = new ViewControllerImpl();
    private final Stage primaryStage;
    private Optional<DynamicView> drawableView = Optional.empty();
    //da modificare
    private Control listener;
    
    
    private ViewControllerImpl() {
        this.primaryStage = new MainStageGenerator().getStage();
    }
    
    public static ViewController getView() {
        return singleton;
    }

    @Override
    public void updateScene(final Map<Entities, List<Pair<Integer, Position>>> entities) {
        final DynamicView dv = this.drawableView.orElseThrow(IllegalStateException::new);
        dv.updateScene(entities);
    }
    
    //stabilire la struttura di comunicazione
    @Override
    public void changeScene() {
        this.drawableView = ViewFactory.createNewScene(this.primaryStage, this.listener);
    }

    @Override
    public void setListener() {
        //da aggiungere la roba
    }

}
