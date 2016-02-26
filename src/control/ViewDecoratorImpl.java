package control;

import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import utility.Dimension;
import utility.Pair;
import view.ViewController;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlComunication;

public class ViewDecoratorImpl implements ViewDecorator{
    private int screenMoltiplicatorFactor = 1;
    private final ViewController view;
    private Dimension levelDimension;
    private final Rectangle2D resolution;
    
    public ViewDecoratorImpl(final ViewController view) {
        this.resolution = Screen.getPrimary().getVisualBounds();
        this.view = view;
    }
    
    public void changeScene(final SceneType sceneType) {
        if(sceneType == SceneType.DRAWABLE) {
            this.view.changeScene(new Pair<SceneType, Dimension2D>(sceneType, new Dimension2D(this.levelDimension.getWidth() * screenMoltiplicatorFactor, levelDimension.getHeight() * screenMoltiplicatorFactor)));
        } else {
            this.view.changeScene(new Pair<SceneType, Dimension2D>(sceneType, new Dimension2D(this.resolution.getWidth()/2, this.resolution.getHeight()/2)));
        }
    }

    @Override
    synchronized public void notifySceneEvent(final Notifications notification) {
        this.view.notifySceneEvent(notification);
    }

    @Override
    public void updateScene(final List<ControlComunication> entities) {
        this.view.updateScene(entities);
    }
    
    public void setLevelDimension(final Dimension levelDimension) {
        this.levelDimension = levelDimension;
        calculateMoltiplicatorFactor();
    }

    private void calculateMoltiplicatorFactor() {
        if(this.levelDimension.getHeight() < this.resolution.getHeight()/2) {
            screenMoltiplicatorFactor = (int) ((this.resolution.getHeight()/2)/this.levelDimension.getHeight() + 1);
        }
    }
    
    public int getScreenMoltiplicatorFactor() {
        return this.screenMoltiplicatorFactor;
    }
    
}
