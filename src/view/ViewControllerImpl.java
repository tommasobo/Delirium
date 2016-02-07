package view;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;
import javafx.stage.Stage;

public class ViewControllerImpl implements ViewController {
    
    private final ViewController singleton = new ViewControllerImpl();
    private final Stage primaryStage;
    private GenericView currentlyDisplayed;
    
    private ViewControllerImpl() {
        this.primaryStage = new MainStageGenerator().getStage();
    }
    
    public ViewController getView() {
        return this.singleton;
    }

    @Override
    public void drawContent(final Map<Entities, List<Pair<Integer, Position>>> entities) {
        this.currentlyDisplayed.updateView(entities);
        if (!this.primaryStage.isShowing()) {
            this.currentlyDisplayed.display();
        }
    }
    
    @Override
    public void changeStage(final boolean type) {
        this.currentlyDisplayed = type ? new ButtonsView(primaryStage) : new DrawableView(primaryStage);
    }

    @Override
    public void getWhatIsAppened() {
        // TODO Auto-generated method stub
    }

}
