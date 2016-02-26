package model.arena.utility;

import model.arena.entities.Point;

@FunctionalInterface
public interface DeterminateNewPoint {

    Point determinateNewPoint(final Point point, final int speed, final Directions direction);

}
