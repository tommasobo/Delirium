package model;

import control.Dimension;
import control.Point;
import control.FisicalProprieties;

public class ModelPosition {
    
    private FisicalProprieties position;
    private ModelDirections direction;
    
    
    public ModelPosition(FisicalProprieties position, ModelDirections direction) {
        this.position = position;
        this.direction = direction;
    }

    //da levare
    public FisicalProprieties getPrimitivePosition() {
        return new FisicalProprieties(new Point(this.position.getPoint().getX(), this.position.getPoint().getY()), this.position.getDimension(), this.position.getSpeed());
    }
    
    public ModelPosition getPosition() {
        return new ModelPosition(new FisicalProprieties(new Point(this.position.getPoint().getX(), this.position.getPoint().getY()), this.position.getDimension(), this.position.getSpeed()), this.direction);
    }

    public Point getPoint() {
        return new Point(this.position.getPoint().getX(), this.position.getPoint().getY());
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
    
    public int getSpeed() {
        return this.position.getSpeed();
    }

    public void setSpeed(int speed) {
        this.position.setSpeed(speed);
    }
    
    
    
    

}
