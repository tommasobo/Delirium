package model;

import control.Dimension;
import control.Point;
import control.PhisicalProprieties;

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
        return new Dimension(this.dimension.getWidth(), this.dimension.getHeight());
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }



    @Override
    public String toString() {
        return "Position [point=" + point + ", direction=" + direction + ", dimension=" + dimension + "]";
    }

    
    
    
    
    
    

}
