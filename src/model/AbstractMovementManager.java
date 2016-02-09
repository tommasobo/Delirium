package model;

import control.Point;
import control.Position;

public abstract class AbstractMovementManager implements MovementManager {

    private final Position position;

    public AbstractMovementManager(Position position) {
        this.position = position;
    }


    abstract public Position getNextMove();
    
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(final Point point, final Position.Directions direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }
    
    
}
