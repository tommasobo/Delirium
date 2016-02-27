package view;

import java.util.List;

import control.Control;
import javafx.geometry.Dimension2D;
import utility.Pair;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlComunication;

/**
 * Represent the view functionalities available to controller.
 */
public interface ViewController {
    /**
     * If a dynamic scene is present, update position and behaviour of entities
     * in the screen.
     * 
     * @param entities
     *            The list of entities to draw and their informations
     * @throws IllegalStateException
     *             When it is called without first initialize a dynamic view
     * @throws IllegalArgumentException
     *             When the parameter is null or in the list are present two
     *             entities with the same ID
     */
    void updateScene(final List<ControlComunication> entities);

    /**
     * Request the initialization of a new scene.
     * 
     * @param settings
     *            A pair containing the type of the new scene and the required
     *            dimension
     * @throws IllegalArgumentException
     *             If a null argument is passed
     */
    void changeScene(final Pair<SceneType, Dimension2D> settings);

    /**
     * Notify to the view special events to be served, like pause, win or lose
     * status.
     * 
     * @param notification
     *            The type of status to represent
     * @throws IllegalArgumentException
     *             If a null argument is passed
     * @throws IllegalStateException
     *             If called without first initialize a dynamic view
     */
    void notifySceneEvent(final Notifications notification);

    /**
     * Set the object that will be used to communicate with the controller. This
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
