package control.viewcomunication;

import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import utility.Dimension;
import utility.Pair;
import view.ViewController;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlCommunication;

/**
 * A decorator for view that makes thread safe methods and calculates scenes
 * dimension starting to screen resolution.
 * 
 * @author Matteo Magnani
 *
 */
public class ViewDecoratorImpl implements ViewDecorator {
    private Double screenMoltiplicatorFactor;
    private final ViewController view;
    private Dimension levelDimension;
    private final Rectangle2D resolution;

    /**
     * 
     * @param view
     *            The view to decorate
     */
    public ViewDecoratorImpl(final ViewController view) {
        this.resolution = Screen.getPrimary().getVisualBounds();
        this.view = view;
    }

    @Override
    public void changeScene(final SceneType sceneType) {
        if (sceneType == SceneType.DRAWABLE) {
            this.view.changeScene(new Pair<SceneType, Dimension2D>(sceneType,
                    new Dimension2D(this.levelDimension.getWidth() * screenMoltiplicatorFactor,
                            levelDimension.getHeight() * screenMoltiplicatorFactor)));
        } else {
            this.view.changeScene(new Pair<SceneType, Dimension2D>(sceneType,
                    new Dimension2D(this.resolution.getWidth() / 2, this.resolution.getHeight() / 2)));
        }
    }

    @Override
    public synchronized void notifySceneEvent(final Notifications notification) {
        this.view.notifySceneEvent(notification);
    }

    @Override
    public void updateScene(final List<ControlCommunication> entities) {
        this.view.updateScene(entities);
    }

    @Override
    public void setLevelDimension(final Dimension levelDimension) {
        this.levelDimension = levelDimension;
        calculateMultiplierFactor();
    }

    /**
     * This method calculates the screen multiplier factor to lock game screen
     * at 3/4 of the screen
     */
    private void calculateMultiplierFactor() {
        this.screenMoltiplicatorFactor = (double) Math
                .round(this.resolution.getHeight() / 4 * 3 / this.levelDimension.getHeight() * 100) / 100;
    }

    @Override
    public Double getScreenMultiplierFactor() {
        return this.screenMoltiplicatorFactor;
    }

}
