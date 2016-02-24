package model;


public class ReactiveMovementManager extends DinamicMovementManager {

    public ReactiveMovementManager(Position position, Bounds bounds, Actions action, int speed, boolean canFly) {
        super(position, bounds, action, speed, canFly);
    }

    @Override
    public Position getNextMove() {
        return applyGravity();
    }
    
}
