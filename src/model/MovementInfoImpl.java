package model;

public class MovementInfoImpl implements MovementInfo {

    private final int speed;
    private final Bounds bounds;
    private final Actions actions;
    private final boolean canFly;
    private final MovementTypes movementTypes;

    public MovementInfoImpl(final int speed, final Bounds bounds, final Actions actions, final boolean canFly,
            final MovementTypes movementTypes) {
        this.speed = speed;
        this.bounds = bounds;
        this.actions = actions;
        this.canFly = canFly;
        this.movementTypes = movementTypes;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(this.bounds.getMinX(), this.bounds.getMaxX(), this.bounds.getMinY(), this.bounds.getMaxY());
    }

    @Override
    public Actions getActions() {
        return this.actions;
    }

    @Override
    public boolean isCanFly() {
        return this.canFly;
    }

    @Override
    public MovementTypes getMovementTypes() {
        return this.movementTypes;
    }

}
