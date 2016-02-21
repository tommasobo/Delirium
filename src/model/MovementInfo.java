package model;

public interface MovementInfo {

    int getSpeed();

    Bounds getBounds();

    Actions getActions();

    boolean isCanFly();

    MovementTypes getMovementTypes();

}