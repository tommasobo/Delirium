package model;

public class ReactiveMovementManager extends DinamicMovementManager {

    public ReactiveMovementManager(final Position position, final Bounds bounds, final Actions action, final int speed,
            final boolean canFly) {
        super(position, bounds, action, speed, canFly);
    }

    @Override
    public Position getNextMove() {
        return super.applyGravity();
    }

}
