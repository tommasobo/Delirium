package view.utilities;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import view.configs.Directions;

/**
 * Object that represent physical properties of an entity as speed or direction.
 */
public class ViewPhysicalProperties {

    private final Point2D point;
    private final Dimension2D dimension;
    private final int speed;
    private final Directions direction;

    /**
     * ViewPhysicalProperties Constructor.
     * 
     * @param x
     *            Entity's X coordinate
     * @param y
     *            Entity's Y coordinate
     * @param width
     *            Entity's width
     * @param height
     *            Entity's height
     * @param speed
     *            Entity's speed
     * @param direction
     *            Entity's direction
     */
    public ViewPhysicalProperties(final int x, final int y, final int width, final int height, final int speed,
            final Directions direction) {
        this.point = new Point2D(x, y);
        this.dimension = new Dimension2D(width, height);
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Get entity's coordinates.
     * 
     * @return JavaFX Point2D
     */
    public Point2D getPoint() {
        return this.point;
    }

    /**
     * Get entity's dimension.
     * 
     * @return JavaFX Dimension2D
     */
    public Dimension2D getDimension() {
        return this.dimension;
    }

    /**
     * Get entity's speed.
     * 
     * @return The entity's speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Get entity's direction.
     * 
     * @return The entity's direction
     */
    public Directions getDirection() {
        return this.direction;
    }
}
