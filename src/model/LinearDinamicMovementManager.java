package model;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    public LinearDinamicMovementManager(ModelPosition position, int speed, Bounds bounds) {
        super(position, speed, bounds);
        
    }

    @Override
    public ModelPosition getNextMove() {
        return this.linearMovement(this.getPosition());
    }

}
