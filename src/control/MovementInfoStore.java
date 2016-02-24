package control;

import model.Actions;
import model.Bounds;
import model.MovementInfo;
import model.MovementTypes;

public class MovementInfoStore implements MovementInfo{
    private int speed;
    private Bounds bounds;
    private Actions actions;
    private boolean canFly;
    private MovementTypes movementTypes;
    
    public MovementInfoStore(int speed, Bounds bounds, Actions actions, boolean canFly, MovementTypes movementTypes) {
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
        return new Bounds(this.bounds.getMinX(), this.bounds.getMaxX(), this.bounds.getMinY(), this.bounds.getMaxY());
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public void setMovementTypes(MovementTypes movementTypes) {
        this.movementTypes = movementTypes;
    }
    
    public MovementInfoStore getCopy() {
        return new MovementInfoStore(this.speed, this.getBounds(), this.actions, this.canFly, this.movementTypes);
    }
    
}
