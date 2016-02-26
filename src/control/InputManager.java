package control;

import java.util.Optional;

import utility.Pair;


public interface InputManager {

    void notifyViewInput(ViewEvents event);

    Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> getNextPGAction();

}