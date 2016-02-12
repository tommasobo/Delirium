package model;


public class StaticMovementManager extends AbstractMovementManager {

    public StaticMovementManager(ModelPosition position, Bounds bounds, boolean canFly) {
        super(position, bounds, canFly);
    }

    @Override
    public ModelPosition getNextMove() {
        return applyGravity(this.getPosition());
    }
    
}
