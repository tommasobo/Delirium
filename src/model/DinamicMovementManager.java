package model;

import control.Position;

public abstract class DinamicMovementManager extends AbstractMovementManager{
    
    
    private final int speed;

    public DinamicMovementManager(Position position, int speed) {
        super(position);
        this.speed = speed;
    }


    protected int getSpeed() {
        return speed;
    }
    
    abstract public Position getNextMove();

}
