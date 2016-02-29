package control.fileloading.levels.storestructures;

import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.MovementTypes;
import model.transfertentities.MovementInfo;

/**
 * Class that contains entities' movement traits with setters.
 * 
 * @author Matteo Magnani
 *
 */
public class MovementInfoStore implements MovementInfo {
    private int speed;
    private Bounds bounds;
    private Actions actions;
    private boolean canFly;
    private MovementTypes movementTypes;

    /**
     * 
     * @param speed
     *            Entity's speed
     * @param bounds
     *            Entity's bounds
     * @param actions
     *            Entity's action
     * @param canFly
     *            Boolean that represents entity ability of fly
     * @param movementTypes
     *            Entity's movement type
     */
    public MovementInfoStore(final int speed, final Bounds bounds, final Actions actions, final boolean canFly,
            final MovementTypes movementTypes) {
        this.speed = speed;
        this.bounds = bounds;
        this.actions = actions;
        this.canFly = canFly;
        this.movementTypes = movementTypes;
    }

    /**
     * 
     * @param m
     *            Movement info to copy
     */
    public MovementInfoStore(final MovementInfoStore m) {
        this.speed = m.getSpeed();
        this.bounds = m.getBounds();
        this.actions = m.getActions();
        this.canFly = m.isCanFly();
        this.movementTypes = m.getMovementTypes();
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(this.bounds.getMinX(), this.bounds.getMaxX(), this.bounds.getMinY(), this.bounds.getMaxY());
    }

    @Override
    public Actions getActions() {
        return actions;
    }

    @Override
    public boolean isCanFly() {
        return canFly;
    }

    @Override
    public MovementTypes getMovementTypes() {
        return movementTypes;
    }

    /**
     * 
     * @param speed
     *            Entity speed
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * 
     * @param bounds
     *            Entity's bounds
     */
    public void setBounds(final Bounds bounds) {
        this.bounds = bounds;
    }

    /**
     * 
     * @param actions
     *            Entity's action
     */
    public void setActions(final Actions actions) {
        this.actions = actions;
    }

    /**
     * 
     * @param canFly
     *            Can entity fly?
     */
    public void setCanFly(final boolean canFly) {
        this.canFly = canFly;
    }

    /**
     * 
     * @param movementTypes
     *            Entity's movement type
     */
    public void setMovementTypes(final MovementTypes movementTypes) {
        this.movementTypes = movementTypes;
    }

}
