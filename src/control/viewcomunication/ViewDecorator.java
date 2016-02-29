package control.viewcomunication;

import java.util.List;

import utility.Dimension;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlCommunication;

/**
 * Interface that declares methods for a view decorator.
 * @author Matteo Magnani
 *
 */
public interface ViewDecorator {
    /**
     * The method changes the view scene with a suitable dimension.
     * 
     * @param sceneType The type of scene
     */
    void changeScene(SceneType sceneType);

    /**
     * Notify event to view.
     * 
     * @param notification The notification
     */
    void notifySceneEvent(Notifications notification);

    /**
     * Update the view game scene.
     * 
     * @param entities
     *            List of entities in game
     */
    void updateScene(final List<ControlCommunication> entities);

    /**
     * Set the level's dimension ad calculate the appropriate screen multiplier
     * factor.
     * 
     * @param levelDimension The level's dimension
     */
    void setLevelDimension(Dimension levelDimension);

    /**
     * 
     * @return The multiplier factor of model's dimensions to make better the
     *         visualization on different screens
     */
    Double getScreenMultiplierFactor();
}
