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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
        result = prime * result + ((point == null) ? 0 : point.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (dimension == null) {
            if (other.dimension != null)
                return false;
        } else if (!dimension.equals(other.dimension))
            return false;
        if (point == null) {
            if (other.point != null)
                return false;
        } else if (!point.equals(other.point))
            return false;
        return true;
    }
    
    
}
