package view.screens;

import java.util.List;

import view.configs.Notifications;
import view.utilities.ControlCommunication;

/**
 * It contains methods that add update functionalities to a generic scene.
 */
public interface DynamicView {
    /**
     * This method updates the scene using informations contained in entities' list.
     * 
     * @param entities
     *            List of entities to be drawn or updated with their
     *            informations
     * @throws IllegalStateException
     *             If called before scene initialization
     */
    void updateScene(final List<ControlCommunication> entities);

    /**
     * This method stops every moving element in the scene and shows a menu.
     * 
     * @param notification
     *            The pause reason
     * @throws IllegalStateException
     *             If called before initialization or first update
     */
    void pauseScene(final Notifications notification);

    /**
     * This method restores the normal behavior of the scene after a pause.
     * 
     * @throws IllegalStateException
     *             If called before initialization or first update
     */
    void playScene();

}
