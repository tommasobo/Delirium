package model;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    public LinearDinamicMovementManager(ModelPosition position, Bounds bounds) {
        super(position, bounds);
        
    }

    @Override
    public ModelPosition getNextMove() {
        return this.linearMovement(this.getPosition());
    }

}
