package control;

public class Position {

    private Point point;
    private final Dimension dimension;
    
    public Position(final Point point, final Dimension dimension) {
        this.point = point;
        this.dimension = dimension;
    }

    public Point getPoint() {
        return new Point(this.point.getX(), this.point.getY());
    }

    public void setPoint(final Point point) {
        this.point = point;
    }

    public Dimension getDimension() {
        return new Dimension(this.dimension.getWidth(), this.dimension.getHeight());
    }
    
}
