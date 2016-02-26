package model.arena.utility;

import model.arena.entities.Point;

public enum Actions {
    MOVE((point, speed, direction) -> (direction == Directions.RIGHT ? new Point(point.getX() + speed, point.getY()) : new Point(point.getX() - speed, point.getY()))),
    MOVEONJUMP((point, speed, direction) -> (direction == Directions.RIGHT ? new Point(point.getX() + speed, point.getY()) : new Point(point.getX() - speed, point.getY() + speed))),
    JUMP((point, speed, direction) -> new Point(point.getX(), point.getY() + speed)),
    MOVEONFALL((point, speed, direction) -> (direction == Directions.RIGHT ? new Point(point.getX() + speed, point.getY()) : new Point(point.getX() - speed, point.getY() - speed))),
    FALL((point, speed, direction) -> new Point(point.getX(), point.getY() - speed)),
    STOP((point, speed, direction) -> point),
    SHOOT((point, speed, direction) -> point);

    private final DeterminateNewPoint function;

    Actions(final DeterminateNewPoint function) {
        this.function = function;
    }

    public Point apply(final Point point, final int speed, final Directions direction) {
        return this.function.determinateNewPoint(point, speed, direction);
    }
}