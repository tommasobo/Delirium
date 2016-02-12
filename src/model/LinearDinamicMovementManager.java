package model;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    public LinearDinamicMovementManager(ModelPosition position, Bounds bounds, boolean canFly) {
        super(position, bounds, canFly);
        
    }

    @Override
    public ModelPosition getNextMove() {
        return this.linearMovement(this.getPosition());
    }

}
