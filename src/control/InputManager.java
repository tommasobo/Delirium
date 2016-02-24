package control;

import java.util.Optional;

import utility.Pair;


public interface InputManager {

    void notifyViewInput(ViewEvents event);

    Pair<model.Actions, Optional<model.Directions>> getNextPGAction();

}