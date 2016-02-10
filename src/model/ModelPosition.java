package model;

import control.Dimension;
import control.Point;
import control.Position;

public class ModelPosition {
    
    private Position position;
    private ModelDirections direction;
    
    public ModelPosition(Position position, ModelDirections direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPrimitivePosition() {
        return new Position(new Point(this.position.getPoint().getX(), this.position.getPoint().getY()), this.position.getDimension());
    }

    public Point getPoint() {
        return this.position.getPoint();
    }

    public void setPoint(final Point point) {
        this.position.setPoint(point);
    }

    public Dimension getDimension() {
        return this.position.getDimension();
    }

    public ModelDirections getDirection() {
        return direction;
    }

    public void setDirection(ModelDirections direction) {
        this.direction = direction;
    }
    
    
    
    

}
