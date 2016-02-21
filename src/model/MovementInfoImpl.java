package model;

public class MovementInfoImpl implements MovementInfo {
    
    private final int speed;
    private final Bounds bounds;
    private final Actions actions;
    private final boolean canFly;
    private final MovementTypes movementTypes;
    
    public MovementInfoImpl(int speed, Bounds bounds, Actions actions, boolean canFly, MovementTypes movementTypes) {
        this.speed = speed;
        this.bounds = bounds;
        this.actions = actions;
        this.canFly = canFly;
        this.movementTypes = movementTypes;
    }

    /* (non-Javadoc)
     * @see model.MovementInfo#getSpeed()
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /* (non-Javadoc)
     * @see model.MovementInfo#getBounds()
     */
    @Override
    public Bounds getBounds() {
        return bounds;
    }

    /* (non-Javadoc)
     * @see model.MovementInfo#getActions()
     */
    @Override
    public Actions getActions() {
        return actions;
    }

    /* (non-Javadoc)
     * @see model.MovementInfo#isCanFly()
     */
    @Override
    public boolean isCanFly() {
        return canFly;
    }

    /* (non-Javadoc)
     * @see model.MovementInfo#getMovementTypes()
     */
    @Override
    public MovementTypes getMovementTypes() {
        return movementTypes;
    }
    
    
    

}
