package model;

import control.Point;

public abstract class AbstractMovementManager implements MovementManager {

    private final ModelPosition position;

    public AbstractMovementManager(ModelPosition position) {
        this.position = position;
    }


    abstract public ModelPosition getNextMove();
    
    
    public ModelPosition getPosition() {
        return position.getPosition();
    }
    
    public void setPosition(final Point point, final ModelDirections direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }
    
    protected void setDirection(final ModelDirections direction) {
        this.position.setDirection(direction);
    }
    
    protected ModelDirections getDirection() {
        return position.getDirection();
    }
    
    
}
