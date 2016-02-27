package control;

import java.util.Optional;

import utility.Pair;

/**
 * Interface that declare methods for a working input manager
 * @author Matteo Magnani
 *
 */
public interface InputManager {

    /**
     * The method take a view event and elaborate it
     * @param event The view event
     */
    void notifyViewInput(ViewEvents event);

    /**
     * 
     * @return A pair that represent the next PG action to perform
     */
    Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> getNextPGAction();

}