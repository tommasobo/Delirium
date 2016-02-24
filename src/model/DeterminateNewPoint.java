package model;

@FunctionalInterface
public interface DeterminateNewPoint {

    Point determinateNewPoint(final Point point, final int speed, final Directions direction);

}
