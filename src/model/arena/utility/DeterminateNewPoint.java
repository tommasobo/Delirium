package model.arena.utility;

import model.arena.entities.Point;

/**
 * This @FunctionalInterface is used in @Actions in order to determinate new
 * point.
 * 
 * @author josephgiovanelli
 *
 */
@FunctionalInterface
public interface DeterminateNewPoint {

    /**
     * This method is applied in @Actions.
     * 
     * @param point
     *            : actual Point of entity.
     * @param speed
     *            : speed of entity.
     * @param direction
     *            : direction of entity.
     * @return : new Point of entity.
     */
    Point determinateNewPoint(final Point point, final int speed, final Directions direction);

}
