package model.transfertentities;

import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.MovementTypes;

public interface MovementInfo {

    int getSpeed();

    Bounds getBounds();

    Actions getActions();

    boolean isCanFly();

    MovementTypes getMovementTypes();

}