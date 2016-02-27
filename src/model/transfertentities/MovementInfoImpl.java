package model.transfertentities;

import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.MovementTypes;

/**
 * This class implements @MovementInfo and offers the same services.
 * 
 * @author josephgiovanelli
 *
 */
public class MovementInfoImpl implements MovementInfo {

    private final int speed;
    private final Bounds bounds;
    private final Actions actions;
    private final boolean canFly;
    private final MovementTypes movementTypes;

    /**
     * This is the basic features that a dynamic entity have to have.
     * 
     * @param bounds
     *            : the limits of his movement.
     * @param actions
     *            : the first action.
     * @param speed
     *            : how much time do an action.
     * @param canFly
     *            : if he can fly.
     * @param movementTypes
     *            : the type of movement of the entity.
     */
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
