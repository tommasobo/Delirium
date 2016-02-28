package view;

import java.util.List;

import control.Control;
import javafx.geometry.Dimension2D;
import utility.Pair;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlCommunication;

/**
 * This interface represents the view's functionalities available to controller.
 */
public interface ViewController {
    /**
     * If a dynamic scene is present, this method updates position and behaviour of entities
     * in the screen.
     * 
     * @param entities
     *            The list of entities to draw and their informations
     * @throws IllegalStateException
     *             When it is called without first initializing a dynamic view
     * @throws IllegalArgumentException
     *             When the parameter is null or in the list two
     *             entities with the same ID are present
     */
    void updateScene(final List<ControlCommunication> entities);

    /**
     * This method requests the initialization of a new scene.
     * 
     * @param settings
     *            A pair containing the type of the new scene and the required
     *            dimension
     * @throws IllegalArgumentException
     *             If a null argument is passed
     */
    void changeScene(final Pair<SceneType, Dimension2D> settings);

    /**
     * This method notifies to the view special events to be served, like pause, win or lose
     * status.
     * 
     * @param notification
     *            The type of status to represent
     * @throws IllegalArgumentException
     *             If a null argument is passed
     * @throws IllegalStateException
     *             If called without first initializing a dynamic view
     */
    void notifySceneEvent(final Notifications notification);

    /**
     * This method sets the object that will be used to communicate with the controller. This
     * method can only be called once.
     * 
     * @param listener
     *            The controller listener
     * @throws IllegalArgumentException
     *             If a null object has been passed
     * @throws IllegalStateException
     *             If the listener is already present
     */
    void setListener(final Control listener);

}
