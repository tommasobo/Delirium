package model;


public class StaticMovementManager extends AbstractMovementManager {

    public StaticMovementManager(Position position, Bounds bounds, int speed, boolean canFly) {
        super(position, bounds, speed, canFly);
    }

    @Override
    public Position getNextMove() {
        return applyGravity(this.getPosition());
    }
    
}
