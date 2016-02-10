package model;

import control.Point;

public abstract class AbstractMovementManager implements MovementManager {

    private final ModelPosition position;

    public AbstractMovementManager(ModelPosition position) {
        this.position = position;
    }


    abstract public ModelPosition getNextMove();
    
    
    public ModelPosition getPosition() {
        return position;
    }
    
    public void setPosition(final Point point, final ModelDirections direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }
    
    
}
