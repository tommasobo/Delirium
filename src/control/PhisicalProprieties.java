package control;

public class PhisicalProprieties {

    private Point point;
    private final Dimension dimension;
    private int speed;
    
    public PhisicalProprieties(final Point point, final Dimension dimension, final int speed) {
        this.point = point;
        this.dimension = dimension;
        this.speed = speed;
    }

    public Point getPoint() {
        return new Point(this.point.getX(), this.point.getY());
    }

    public void setPoint(final Point point) {
        this.point = point;
    }

    public Dimension getDimension() {
        return new DimensionImpl(this.dimension.getWidth(), this.dimension.getHeight());
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
        PhisicalProprieties other = (PhisicalProprieties) obj;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
}
