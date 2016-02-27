package control.viewcomunication;

import java.util.List;

import utility.Dimension;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlComunication;

public interface ViewDecorator {
    //TODO brutto
    /**
     * The method change the view scene with a suitable dimension
     * @param sceneType
     */
    void changeScene(SceneType sceneType);
    /**
     * Notify event to view
     * @param notification
     */
    void notifySceneEvent(Notifications notification);
    /**
     * Update the view game scene
     * @param entities List of entities in game
     */
    void updateScene(final List<ControlComunication> entities);
    /**
     * Set the level dimension ad calculate the appropriate screen multiplier factor
     * @param levelDimension
     */
    void setLevelDimension(Dimension levelDimension);
    
    /**
     * 
     * @return The multiplier factor of model's dimensions to make better the visualization on different screens
     */
    int getScreenMultiplierFactor();
}
