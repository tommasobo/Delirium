package model;

import control.Dimension;
import control.DimensionImpl;
import control.Point;

public class Position {
    
    private Point point;
    private Directions direction;
    private Dimension dimension;
    
    
    
    public Position(Point point, Directions direction, Dimension dimension) {
        this.point = point;
        this.direction = direction;
        this.dimension = dimension;
    }



    public Point getPoint() {
        return new Point(this.point.getX(), this.point.getY());
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public Dimension getDimension() {
        return new DimensionImpl(this.dimension.getWidth(), this.dimension.getHeight());
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }



    @Override
    public String toString() {
        return "Position [point=" + point + ", direction=" + direction + ", dimension=" + dimension + "]";
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
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
        if (direction != other.direction)
            return false;
        if (point == null) {
            if (other.point != null)
                return false;
        } else if (!point.equals(other.point))
            return false;
        return true;
    }

    
    
    
    
    
    

}
