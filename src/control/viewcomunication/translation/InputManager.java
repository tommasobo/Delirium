package control.viewcomunication.translation;

import java.util.Optional;

import control.viewcomunication.ViewEvents;
import utility.Pair;

/**
 * Interface that declares methods for a working input manager
 * 
 * @author Matteo Magnani
 *
 */
public interface InputManager {

    /**
     * The method takes a view event and elaborate it
     * 
     * @param event
     *            The view event
     */
    void notifyViewInput(ViewEvents event);

    /**
     * 
     * @return A pair that represents the next PG action to perform
     */
    Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> getNextPGAction();

}