package model.arena.entities;

import model.arena.utility.Directions;
import utility.Dimension;

public class Position {

    private Point point;
    private Directions direction;
    private Dimension dimension;

    public Position(final Point point, final Directions direction, final Dimension dimension) {
        this.point = point;
        this.direction = direction;
        this.dimension = dimension;
    }

    public Point getPoint() {
        return new Point(this.point.getX(), this.point.getY());
    }

    public void setPoint(final Point point) {
        this.point = point;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

    public Dimension getDimension() {
        return new Dimension(this.dimension.getWidth(), this.dimension.getHeight());
    }

    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }

}
