package control;

import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import view.ControlComunication;
import view.Notifications;
import view.SceneType;
import view.ViewController;

public class ViewDecoratorImpl implements ViewDecorator{
    public static int SCREENMOLTIPLICATORFACTOR = 1;
    private final ViewController view;
    private Dimension levelDimension;
    private final Rectangle2D resolution;
    
    public ViewDecoratorImpl(ViewController view) {
        this.resolution = Screen.getPrimary().getVisualBounds();
        this.view = view;
    }
    
    public void changeScene(SceneType sceneType) {
        if(sceneType == SceneType.DRAWABLE) {
            //System.out.println(SCREENMOLTIPLICATORFACTOR + " " + resolution);
            //System.out.println(levelDimension);
            this.view.changeScene(new Pair<SceneType, Dimension2D>(sceneType, new Dimension2D(this.levelDimension.getWidth() * SCREENMOLTIPLICATORFACTOR, levelDimension.getHeight() * SCREENMOLTIPLICATORFACTOR)));
        } else {
            this.view.changeScene(new Pair<SceneType, Dimension2D>(sceneType, new Dimension2D(this.resolution.getWidth()/2, this.resolution.getHeight()/2)));
        }
    }

    @Override
    public void notifySceneEvent(Notifications notification) {
        this.view.notifySceneEvent(notification);
    }

    @Override
    public void updateScene(List<ControlComunication> entities) {
        this.view.updateScene(entities);
    }
    
    public void setLevelDimension(Dimension levelDimension) {
        this.levelDimension = levelDimension;
        calculateMoltiplicatorFactor();
    }

    private void calculateMoltiplicatorFactor() {
        if(this.levelDimension.getHeight() < this.resolution.getHeight()/2) {
            SCREENMOLTIPLICATORFACTOR = (int) ((this.resolution.getHeight()/2)/this.levelDimension.getHeight() + 1);
        }
    }
    
}
