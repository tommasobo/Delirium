package control;

public class Position {
    
    public static enum Directions {
        LEFT,
        RIGHT;
    }

    private Point point;
    private Directions direction;
    private final Dimension dimension;
    
    public Position(final Point point, final Directions direction, final Dimension dimension) {
        this.point = point;
        this.direction = direction;
        this.dimension = dimension;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(final Point point) {
        this.point = point;
    }

    public Directions getDirection() {
        return this.direction;
    }

    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

    public Dimension getDimension() {
        return this.dimension;
    }
    
}
