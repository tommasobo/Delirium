package model.arena.utility;

import model.arena.entities.Point;
/**
 * This enum use the @FunctionalInterface @DeterminateNewPoint so, decide the
 * future movement of the entity.
 * 
 * @author josephgiovanelli
 *
 */
public enum Actions {
    /**
     * The new point is a result from point, speed and direction.
     */
    MOVE((point, speed, direction) -> (direction == Directions.RIGHT ? new Point(point.getX() + speed, point.getY()) : new Point(point.getX() - speed, point.getY()))),
    /**
     * The new point is a result from point, speed and direction.
     */
    MOVEONJUMP((point, speed, direction) -> (direction == Directions.RIGHT ? new Point(point.getX() + speed, point.getY()) : new Point(point.getX() - speed, point.getY() + speed))),
    /**
     * The new point is a result from point, speed.
     */
    JUMP((point, speed, direction) -> new Point(point.getX(), point.getY() + speed)),
    /**
     * The new point is a result from point, speed and direction.
     */
    MOVEONFALL((point, speed, direction) -> (direction == Directions.RIGHT ? new Point(point.getX() + speed, point.getY()) : new Point(point.getX() - speed, point.getY() - speed))),
    /**
     * The new point is a result from point, speed.
     */
    FALL((point, speed, direction) -> new Point(point.getX(), point.getY() - speed)),
    /**
     * The new point is actual point.
     */
    STOP((point, speed, direction) -> point),
    /**
     * The new point is actual point, indeed isn't a movement action.
     */
    SHOOT((point, speed, direction) -> point),
    /**
     * The new point is actual point, indeed isn't a movement action.
     */
    BEND((point, speed, direction) -> point),
    
    UNBEND((point, speed, direction) -> point);
    

    private final DeterminateNewPoint function;

    Actions(final DeterminateNewPoint function) {
        this.function = function;
    }

    /**
     * This method apply the lambda function with param.
     * @param point : actual point
     * @param speed : the speed of the entity.
     * @param direction : the direction of the entity.
     * @return : new point of the entity.
     */
    public Point apply(final Point point, final int speed, final Directions direction) {
        return this.function.determinateNewPoint(point, speed, direction);
    }
}
