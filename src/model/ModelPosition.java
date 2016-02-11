package model;

import control.Dimension;
import control.Point;
import control.PhisicalProprieties;

public class ModelPosition {
    
    private PhisicalProprieties position;
    private ModelDirections direction;
    
    
    public ModelPosition(PhisicalProprieties position, ModelDirections direction) {
        this.position = position;
        this.direction = direction;
    }

    //da levare
    public PhisicalProprieties getPrimitivePosition() {
        return new PhisicalProprieties(new Point(this.position.getPoint().getX(), this.position.getPoint().getY()), this.position.getDimension(), this.position.getSpeed());
    }
    
    public ModelPosition getPosition() {
        return new ModelPosition(new PhisicalProprieties(new Point(this.position.getPoint().getX(), this.position.getPoint().getY()), this.position.getDimension(), this.position.getSpeed()), this.direction);
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
