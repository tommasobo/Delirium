package model.arena.entities.movement;

import model.arena.entities.Point;
import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.Directions;

public interface MovementManager {

    Position getPosition();

    void setPosition(final Point point, final Directions direction);

    int getSpeed();

    void setSpeed(final int speed);

    Bounds getBounds();

    boolean isCanFly();

    Position getNextMove();

    void setAction(final Actions action);

    Actions getAction();
}
