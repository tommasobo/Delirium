package model.arena.entities;

import model.arena.utility.Directions;
import utility.Dimension;

/**
 * This class decorated the @Point, the @Directions and the @Dimension. It
 * contains the physical properties of the entities.
 * 
 * @author josephgiovanelli
 *
 */
public class Position {

    private Point point;
    private Directions direction;
    private Dimension dimension;

    /**
     * This constructor initialized the fields.
     * 
     * @param point
     *            : the point that you want to use.
     * @param direction
     *            : the first direction that start the entity.
     * @param dimension
     *            : how much is big the entity.
     */
    public Position(final Point point, final Directions direction, final Dimension dimension) {
        this.point = point;
        this.direction = direction;
        this.dimension = dimension;
    }

    /**
     * Get the point of the entity.
     * 
     * @return : the point field.
     */
    public Point getPoint() {
        return new Point(this.point.getX(), this.point.getY());
    }

    /**
     * Change the point of the entity.
     * 
     * @param point
     *            : the value that you want to set.
     */
    public void setPoint(final Point point) {
        this.point = point;
    }

    /**
     * Get the direction of the entity.
     * 
     * @return : the direction field.
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     * Change the Direction of the entity.
     * 
     * @param direction
     *            : the value that you want to set.
     */
    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

    /**
     * Get the dimension of the entity.
     * 
     * @return : the dimension field.
     */
    public Dimension getDimension() {
        return new Dimension(this.dimension.getWidth(), this.dimension.getHeight());
    }

    /**
     * Change the Dimension of the entity.
     * 
     * @param dimension
     *            : the value that you want to set.
     */
    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }

}
